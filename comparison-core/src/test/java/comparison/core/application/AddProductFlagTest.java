package comparison.core.application;

import comparison.core.domain.Product;
import comparison.core.domain.Products;
import comparison.core.infrastructure.InMemoryProducts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static comparison.core.domain.ProductTestBuilder.aProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddProductFlagTest {
    private Products products;
    private AddProductFlag addProductFlag;

    @BeforeEach
    void setUp() {
        products = new InMemoryProducts();
        addProductFlag = new AddProductFlag(products);
    }

    @Test
    void itShouldAddFlag() {
        Product product = products.add(aProduct().build());

        addProductFlag.execute(new AddProductFlag.AddProductFlagRequest(
                product.id(),
                "one",
                "positive"
        ));

        assertEquals(1, product.flags().size());
        assertEquals("one", product.flags().get(0).description());
    }

    @Test
    void addFlagOnNonExistingProductShouldFail() {
        assertThrows(RuntimeException.class, () ->
                addProductFlag.execute(new AddProductFlag.AddProductFlagRequest(
                        "xxx",
                        "one",
                        "positive")));
    }
}
