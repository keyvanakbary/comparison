package comparison.core.infrastructure;

import comparison.core.domain.Products;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;

public class SqlProductsTest extends ProductsTest {
    private static SqlTestDatabase database;

    @BeforeAll
    static void setupDatabase() throws SQLException {
        database = SqlTestDatabase.getInstance();
    }

    @Override
    protected Products createProductRepository() {
        return new SqlProducts(database.namedParameterJdbcTemplate());
    }

    @AfterEach
    void resetDatabase() throws SQLException {
        database.reset();
    }
}
