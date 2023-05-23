package comparison.core.infrastructure;

import comparison.core.domain.Product;
import comparison.core.domain.Products;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryProducts implements Products {
    private final Map<String, Product> products = new HashMap<>();

    @Override
    public Product add(Product product) {
        products.put(product.id(), product);

        return product;
    }

    @Override
    public Optional<Product> byProvider(int providerId, int productId) {
        return products.values().stream()
                .filter(p -> p.providerId() == providerId && p.providerProductId() == productId)
                .findFirst();
    }

    @Override
    public List<Product> all() {
        return products.values().stream().toList();
    }

    @Override
    public Optional<Product> byId(String id) {
        return products.values().stream()
                .filter(p -> p.id().equals(id))
                .findFirst();
    }
}
