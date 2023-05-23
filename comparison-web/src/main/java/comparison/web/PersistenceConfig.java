package comparison.web;

import comparison.core.domain.Products;
import comparison.core.infrastructure.SqlProducts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class PersistenceConfig {
    @Bean
    public Products productRepository(NamedParameterJdbcTemplate jdbc) {
        return new SqlProducts(jdbc);
    }
}
