package comparison.core.domain;

import java.util.List;
import java.util.Optional;

public interface Products {
    Product add(Product product);

    Optional<Product> byProvider(int providerId, int productId);

    List<Product> all();

    Optional<Product> byId(String id);
}
