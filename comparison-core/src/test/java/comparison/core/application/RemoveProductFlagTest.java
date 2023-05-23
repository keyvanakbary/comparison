package comparison.core.application;

import comparison.core.domain.Flag;
import comparison.core.domain.Product;
import comparison.core.domain.Products;
import comparison.core.infrastructure.InMemoryProducts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static comparison.core.domain.ProductTestBuilder.aProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveProductFlagTest {
    private Products products;
    private RemoveProductFlag removeProductFlag;

    @BeforeEach
    void setUp() {
        products = new InMemoryProducts();
        removeProductFlag = new RemoveProductFlag(products);
    }

    @Test
    void itShouldRemoveFlag() {
        Product product = products.add(aProduct().build());
        product.addFlag(Flag.positive("one"));
        product.addFlag(Flag.positive("two"));

        removeProductFlag.execute(new RemoveProductFlag.RemoveProductFlagRequest(
                product.id(),
                1
        ));

        assertEquals(1, product.flags().size());
        assertEquals("one", product.flags().get(0).description());
    }

    @Test
    void removeFlagOnNonExistingProductShouldFail() {
        assertThrows(RuntimeException.class, () ->
                removeProductFlag.execute(new RemoveProductFlag.RemoveProductFlagRequest(
                        "xxx",
                        1)));
    }
}
