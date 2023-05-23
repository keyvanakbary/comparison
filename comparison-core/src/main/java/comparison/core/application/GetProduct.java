package comparison.core.application;

import comparison.core.domain.Product;
import comparison.core.domain.Products;

public record GetProduct(Products products) {
    public Product execute(String id) {
        return products.byId(id).orElseThrow(RuntimeException::new);
    }
}
