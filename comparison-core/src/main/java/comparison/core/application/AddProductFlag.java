package comparison.core.application;

import comparison.core.domain.Flag;
import comparison.core.domain.FlagType;
import comparison.core.domain.Product;
import comparison.core.domain.Products;

public record AddProductFlag(Products products) {
    public void execute(AddProductFlagRequest request) {
        Product product = products.byId(request.productId()).orElseThrow(RuntimeException::new);

        product.addFlag(new Flag(request.description(), FlagType.valueOf(request.type().toUpperCase())));

        products.add(product);
    }

    public record AddProductFlagRequest(String productId, String description, String type) {}
}
