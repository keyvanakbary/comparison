package comparison.importer.financeads;

import comparison.importer.financeads.Country;
import comparison.core.domain.Product;
import comparison.importer.financeads.FinanceAdsProductProvider;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FinanceAdsProductProviderTest {
    @Test
    void itShouldFetchProducts() {
        List<Product> products = new FinanceAdsProductProvider(new XmlParser(), Country.GERMANY).fetch();

        assertFalse(products.isEmpty(), "It should fetch at least 1 product");
    }
}
