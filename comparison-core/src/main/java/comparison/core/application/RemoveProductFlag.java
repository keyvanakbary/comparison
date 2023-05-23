package comparison.core.application;

import comparison.core.domain.Product;
import comparison.core.domain.Products;

public record RemoveProductFlag(Products products) {
    public void execute(RemoveProductFlagRequest request) {
        Product product = products.byId(request.productId()).orElseThrow(RuntimeException::new);

        product.removeFlag(request.index());

        products.add(product);
    }

    public record RemoveProductFlagRequest(String productId, int index) {}
}
