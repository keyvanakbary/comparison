package comparison.core.infrastructure;

import org.flywaydb.core.Flyway;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

@Testcontainers
public class SqlTestDatabase {
    @Container
    public static GenericContainer<?> mysql = new GenericContainer<>(DockerImageName.parse("mysql/mysql-server:8.0"))
            .withEnv(new HashMap<>() {{
                put("MYSQL_ROOT_PASSWORD", "root");
                put("MYSQL_USER", "comparison");
                put("MYSQL_PASSWORD", "comparison");
                put("MYSQL_DATABASE", "comparison");
            }})
            .withExposedPorts(3306);

    private final Connection connection;
    private final DataSource dataSource;

    private static SqlTestDatabase instance;

    private SqlTestDatabase(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        connection.setAutoCommit(false);

        dataSource = new SingleConnectionDataSource(connection, true);

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();

        connection.commit();
    }

    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(jdbcTemplate());
    }

    public void reset() throws SQLException {
        connection.rollback();
    }

    public static SqlTestDatabase getInstance() throws SQLException {
        if (instance == null) {
            mysql.start();
            instance = new SqlTestDatabase("jdbc:mysql://" + mysql.getHost() + ":" + mysql.getFirstMappedPort() + "/comparison", "comparison", "comparison");
        }

        return instance;
    }
}
