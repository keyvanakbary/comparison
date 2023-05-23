package comparison.core.application;

import comparison.core.domain.Product;
import comparison.core.domain.Products;

import java.util.List;

public record ListProducts(Products products) {
    public List<Product> execute() {
        return products.all();
    }
}
