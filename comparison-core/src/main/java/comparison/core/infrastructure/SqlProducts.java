package comparison.core.infrastructure;

import comparison.core.domain.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.util.stream.Collectors.joining;

public class SqlProducts implements Products {
    private final NamedParameterJdbcTemplate jdbc;

    public SqlProducts(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private static final String SELECT_PRODUCTS_JOIN_FLAGS_AND_EDITS =
            "SELECT * FROM products " +
            "LEFT JOIN product_flags AS flag ON flag.product_id = products.id " +
            "LEFT JOIN product_edits AS edit ON edit.product_id = products.id ";

    @Override
    public Product add(Product product) {
        upsert("products", new HashMap<>() {{
            put("id", product.id());
            put("provider_id", product.providerId());
            put("link", product.info().link());
            put("logo", product.info().logo());
            put("bank_id", product.info().bankId());
            put("provider_product_id", product.info().productId());
            put("extra_info", product.info().extraInfo());
            put("bank_name", product.info().bankName());
            put("product_title", product.info().productTitle());
            put("rating", product.info().rating());
            put("has_rating", product.info().hasRating());
            put("apr", product.info().apr());
            put("yearly_fee", product.info().yearlyFee());
            put("yearly_euro_fee", product.info().yearlyEuroFee());
            put("offers_bonus_program", product.info().offersBonusProgram());
            put("offers_insurance", product.info().offersInsurance());
            put("offers_benefits", product.info().offersBenefits());
            put("offers_extra_services", product.info().offersExtraServices());
            put("application_requirements", product.info().applicationRequirements());
            put("participation_fee", product.info().participationFee());
            put("participation_cost", product.info().participationCost());
            put("first_year_fee", product.info().firstYearFee());
            put("second_year_fee", product.info().secondYearFee());
            put("national_atm_fee", product.info().nationalAtmFee());
            put("international_atm_fee", product.info().internationalAtmFee());
            put("national_atm_free_fee", product.info().nationalAtmFreeFee());
            put("international_atm_free_fee", product.info().internationalAtmFreeFee());
            put("apr_amount", product.info().aprAmount());
            put("interest_rate", product.info().interestRate());
            put("card_type", product.info().cardType().toString().toLowerCase());
            put("free_credit_fee_euro_atm", product.info().freeCreditFeeEuroAtm());
        }}, "id");

        clearInfoEdits(product);
        recreateInfoEdits(product);

        clearFlags(product);
        recreateFlags(product);

        return product;
    }

    private void recreateFlags(Product product) {
        if (product.flags().isEmpty()) {
            return;
        }
        List<Object[]> batch = product.flags().stream()
                .map(flag -> new Object[]{
                        product.id(),
                        flag.description(),
                        flag.type() == FlagType.POSITIVE ? "positive" : "negative"
                })
                .toList();

        jdbc.update("INSERT INTO product_flags (product_id, description, flag_type) VALUES :batch", new HashMap<>() {{
            put("batch", batch);
        }});
    }

    private void clearFlags(Product product) {
        jdbc.update("DELETE FROM product_flags WHERE product_id = :product_id", new HashMap<>() {{
            put("product_id", product.id());
        }});
    }

    private void recreateInfoEdits(Product product) {
        if (product.infoEdit() == null) {
            return;
        }

        upsert("product_edits", new HashMap<>() {{
            put("product_id", product.id());
            put("link", product.infoEdit().link());
            put("logo", product.infoEdit().logo());
            put("extra_info", product.infoEdit().extraInfo());
            put("bank_name", product.infoEdit().bankName());
            put("product_title", product.infoEdit().productTitle());
            put("application_requirements", product.infoEdit().applicationRequirements());
            put("participation_fee", product.infoEdit().participationFee());
            put("participation_cost", product.infoEdit().participationCost());
        }}, "product_id");
    }

    private void clearInfoEdits(Product product) {
        jdbc.update("DELETE FROM product_edits WHERE product_id = :product_id", new HashMap<>() {{
            put("product_id", product.id());
        }});
    }

    @Override
    public Optional<Product> byProvider(int providerId, int productId) {
        return fetchProducts(SELECT_PRODUCTS_JOIN_FLAGS_AND_EDITS + " WHERE provider_id = :provider_id AND provider_product_id = :provider_product_id", new HashMap<>() {{
            put("provider_id", providerId);
            put("provider_product_id", productId);
        }}).stream().findFirst();
    }

    @Override
    public Optional<Product> byId(String id) {
        return fetchProducts(SELECT_PRODUCTS_JOIN_FLAGS_AND_EDITS + " WHERE products.id = :id", new HashMap<>() {{
            put("id", id);
        }}).stream().findFirst();
    }

    private void upsert(String table, Map<String, Object> params, String primaryKey) {
        var withoutPrimary = new HashMap<>(params);
        withoutPrimary.remove(primaryKey);

        String keys = params.keySet().stream().map(Object::toString).collect(joining(","));
        String values = params.keySet().stream().map(key -> ":" + key).collect(joining(","));
        String duplicate = withoutPrimary.keySet().stream().map(key -> key + " = :" + key).collect(joining(","));

        String sql =
                "INSERT INTO " + table + " (" + keys + ") VALUES (" + values + ") " +
                "ON DUPLICATE KEY UPDATE " + duplicate;

        jdbc.update(sql, params);
    }

    @Override
    public List<Product> all() {
        return fetchProducts(SELECT_PRODUCTS_JOIN_FLAGS_AND_EDITS + " ORDER BY yearly_fee DESC", new HashMap<>());
    }

    private List<Product> fetchProducts(String sql, Map<String, Object> params) {
        return jdbc.query(sql, params, rs -> {
            Map<String, Product> products = new LinkedHashMap<>();//preserves insertion order
            while (rs.next()) {
                String id = rs.getString("id");

                Product product = products.get(id);
                if (product == null) {
                    products.put(id, toProduct(rs));
                }

                if (rs.getString("flag.product_id") != null) {
                    products.get(id).addFlag(new Flag(
                            rs.getString("flag.description"),
                            rs.getString("flag.flag_type").equals("positive") ?
                                    FlagType.POSITIVE : FlagType.NEGATIVE
                    ));
                }

                if (rs.getString("edit.product_id") != null) {
                    products.get(id).editInfo(new InfoEdit(
                            rs.getString("edit.link"),
                            rs.getString("edit.logo"),
                            rs.getString("edit.extra_info"),
                            rs.getString("edit.bank_name"),
                            rs.getString("edit.product_title"),
                            rs.getString("edit.application_requirements"),
                            rs.getString("edit.participation_fee"),
                            rs.getString("edit.participation_cost")
                    ));
                }
            }

            return new ArrayList<>(products.values());
        });
    }

    private static Product toProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getString("id"),
                rs.getInt("provider_id"),
                rs.getInt("provider_product_id"),
                new Info(
                        rs.getString("link"),
                        rs.getString("logo"),
                        rs.getInt("bank_id"),
                        rs.getInt("provider_product_id"),
                        rs.getString("extra_info"),
                        rs.getString("bank_name"),
                        rs.getString("product_title"),
                        rs.getFloat("rating"),
                        rs.getBoolean("has_rating"),
                        rs.getDouble("apr"),
                        rs.getDouble("yearly_fee"),
                        rs.getDouble("yearly_euro_fee"),
                        rs.getBoolean("offers_bonus_program"),
                        rs.getBoolean("offers_insurance"),
                        rs.getBoolean("offers_benefits"),
                        rs.getBoolean("offers_extra_services"),
                        rs.getString("application_requirements"),
                        rs.getString("participation_fee"),
                        rs.getString("participation_cost"),
                        rs.getDouble("first_year_fee"),
                        rs.getDouble("second_year_fee"),
                        rs.getDouble("national_atm_fee"),
                        rs.getDouble("international_atm_fee"),
                        rs.getDouble("national_atm_free_fee"),
                        rs.getDouble("international_atm_free_fee"),
                        rs.getDouble("apr_amount"),
                        rs.getDouble("interest_rate"),
                        CardType.valueOf(rs.getString("card_type").toUpperCase()),
                        rs.getDouble("free_credit_fee_euro_atm")
                ),
                null,
                new ArrayList<>()
        );
    }

}
