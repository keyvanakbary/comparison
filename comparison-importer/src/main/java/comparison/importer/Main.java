package comparison.importer;

import comparison.core.application.ImportProducts;
import comparison.core.domain.Products;
import comparison.core.infrastructure.SqlProducts;
import comparison.importer.financeads.Country;
import comparison.importer.financeads.FinanceAdsProductProvider;
import comparison.importer.financeads.XmlParser;
import org.flywaydb.core.Flyway;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/comparison", "comparison", "comparison");
        DataSource dataSource = new SingleConnectionDataSource(connection, true);

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();

        Products products = new SqlProducts(new NamedParameterJdbcTemplate(new JdbcTemplate(dataSource)));
        new ImportProducts(products, new FinanceAdsProductProvider(new XmlParser(), Country.GERMANY)).execute();
    }
}