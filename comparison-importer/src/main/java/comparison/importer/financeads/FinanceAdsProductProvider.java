package comparison.importer.financeads;

import comparison.core.domain.CardType;
import comparison.core.domain.Info;
import comparison.core.domain.Product;
import comparison.core.domain.ProductProvider;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

public class FinanceAdsProductProvider implements ProductProvider {
    private static final int PROVIDER_ID = 1;
    private static final String API_URL = "https://tools.financeads.net/webservice.php?wf=1&format=xml&calc=kreditkarterechner&country=$country";

    private final XmlParser xmlParser;
    private final Country country;

    public FinanceAdsProductProvider(XmlParser xmlParser, Country country) {
        this.xmlParser = xmlParser;
        this.country = country;
    }

    public List<Product> fetch() {
        try {
            URL url = new URL(API_URL.replace("$country", country.iso2().toUpperCase()));

            List<ProductData> products = xmlParser.parse(url);

            return products.stream().map(FinanceAdsProductProvider::toProduct).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Product toProduct(ProductData data) {
        return new Product(
                UUID.randomUUID().toString(),
                PROVIDER_ID,
                data.productId(),
                new Info(
                        data.link(),
                        data.logo(),
                        data.bankId(),
                        data.productId(),
                        data.extraInfo(),
                        data.bankName(),
                        data.productTitle(),
                        data.rating(),
                        data.hasRating(),
                        data.apr(),
                        data.yearlyFee(),
                        data.yearlyEuroFee(),
                        data.offersBonusProgram(),
                        data.offersInsurance(),
                        data.offersBenefits(),
                        data.offersExtraServices(),
                        data.applicationRequirements(),
                        data.participationFee(),
                        data.participationCost(),
                        data.firstYearFee(),
                        data.secondYearFee(),
                        data.nationalAtmFee(),
                        data.internationalAtmFee(),
                        data.nationalAtmFreeFee(),
                        data.internationalAtmFreeFee(),
                        data.aprAmount(),
                        data.interestRate(),
                        CardType.valueOf(data.cardType().toUpperCase()),
                        data.freeCreditFeeEuroAtm()
                ),
                null,
                List.of()
        );
    }
}