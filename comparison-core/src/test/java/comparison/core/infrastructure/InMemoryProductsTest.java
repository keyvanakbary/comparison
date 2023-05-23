package comparison.core.infrastructure;

import comparison.core.domain.Products;

public class InMemoryProductsTest extends ProductsTest {
    @Override
    protected Products createProductRepository() {
        return new InMemoryProducts();
    }
}
