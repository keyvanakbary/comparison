package comparison.core.infrastructure;

import comparison.core.domain.*;
import comparison.core.domain.InfoTestBuilder;
import comparison.core.domain.ProductTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class ProductsTest {

    private Products products;

    abstract protected Products createProductRepository();

    @BeforeEach
    void setUp() {
        products = createProductRepository();
    }

    @Test
    void itShouldPersistProduct() {
        Product product = ProductTestBuilder.aProduct().build();

        products.add(product);

        Product found = products.byId(product.id()).orElse(null);
        assertNotNull(found);
        assertEquals(product, found);
    }

    @Test
    void itShouldUpdateProductWhenExists() {
        Product product = products.add(ProductTestBuilder.aProduct().build());

        products.add(
                ProductTestBuilder.aProduct()
                        .id(product.id())
                        .info(InfoTestBuilder.anInfo().productTitle("Test").build())
                        .build()
        );

        List<Product> found = products.all();
        assertEquals(1, found.size());
        assertEquals("Test", found.get(0).info().productTitle());
    }

    @Test
    void itShouldAddMultipleProducts() {
        Product p1 = products.add(ProductTestBuilder.aProduct().build());
        Product p2 = products.add(ProductTestBuilder.aProduct().build());
        Product p3 = products.add(ProductTestBuilder.aProduct().build());

        List<Product> found = products.all();

        assertEquals(3, found.size());
        assertTrue(found.containsAll(List.of(p1, p2, p3)));
    }

    @Test
    void itShouldPersistFlags() {
        List<Flag> flags = List.of(Flag.positive("one"), Flag.negative("two"));
        Product product = products.add(ProductTestBuilder.aProduct().flags(flags).build());

        Product found = products.byId(product.id()).orElse(null);
        assertNotNull(found);
        assertEquals(flags, found.flags());
    }

    @Test
    void itShouldPersistInfoEdits() {
        InfoEdit edit = new InfoEdit(
                "https://example.com",
                "https://example.com/logo.png",
                "Extra info",
                "Bank name",
                "Product title",
                "Application requirements",
                "Participation fee",
                "Participation cost"
        );

        Product product = products.add(ProductTestBuilder.aProduct().infoEdit(edit).build());

        Product found = products.byId(product.id()).orElse(null);
        assertNotNull(found);
        assertEquals(edit, found.infoEdit());
    }
}
