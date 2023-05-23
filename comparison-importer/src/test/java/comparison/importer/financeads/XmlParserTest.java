package comparison.importer.financeads;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class XmlParserTest {
    private XmlParser parser;

    @BeforeEach
    void setUp() {
        parser = new XmlParser();
    }

    @Test
    void itShouldParseXml() throws IOException {
        String xml = new String(getClass().getClassLoader().getResourceAsStream("products.xml").readAllBytes());
        List<ProductData> products = parser.parse(xml);

        assertEquals(1, products.size());
        ProductData data = products.get(0);
        assertEquals(3364, data.productId());
        assertTrue(data.hasRating());
        assertEquals(4.7F, data.rating());
        assertEquals(2100.0, data.apr());
        assertEquals("0,00 &euro;", data.participationFee());
    }
}
