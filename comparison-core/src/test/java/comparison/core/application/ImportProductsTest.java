package comparison.core.application;

import comparison.core.domain.Product;
import comparison.core.domain.ProductTestBuilder;
import comparison.core.domain.Products;
import comparison.core.infrastructure.InMemoryProducts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImportProductsTest {
    private Products products;
    private List<Product> providerProducts;
    private ImportProducts importProducts;

    @BeforeEach
    void setUp() {
        products = new InMemoryProducts();
        providerProducts = new ArrayList<>();
        importProducts = new ImportProducts(products, () -> providerProducts);
    }

    @Test
    void itShouldInsertNewProductsFromProvider() {
        providerProducts.add(ProductTestBuilder.aProduct().build());
        providerProducts.add(ProductTestBuilder.aProduct().build());

        importProducts.execute();

        List<Product> inserted = products.all();
        assertEquals(2, inserted.size());
    }

    @Test
    void itShouldUpdateWhenProviderExists() {
        Product existing = ProductTestBuilder.aProduct().providerId(1).providerProductId(2).build();
        Product imported = ProductTestBuilder.aProduct().providerId(1).providerProductId(2).build();
        products.add(existing);
        providerProducts.add(imported);

        importProducts.execute();

        List<Product> inserted = products.all();
        assertEquals(1, inserted.size());
        assertEquals(existing.id(), inserted.get(0).id(), "Id should be maintained");
    }
}
