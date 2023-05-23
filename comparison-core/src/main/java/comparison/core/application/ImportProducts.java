package comparison.core.application;

import comparison.core.domain.ProductProvider;
import comparison.core.domain.Product;
import comparison.core.domain.Products;

public record ImportProducts(
        Products products,
        ProductProvider provider
) {
    public void execute() {
        provider.fetch()
                .forEach(product -> {
                    Product p = products
                        .byProvider(product.providerId(), product.providerProductId())
                        .map(existing -> {
                            existing.updateInfo(product.info());

                            return existing;
                        })
                        .orElse(product);
                    products.add(p);
                });
    }
}
