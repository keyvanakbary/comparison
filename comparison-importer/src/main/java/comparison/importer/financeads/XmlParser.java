package comparison.importer.financeads;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class XmlParser {
    private final XmlMapper mapper;

    public XmlParser() {
        Module unmarshalling = new SimpleModule("Unmarshalling", Version.unknownVersion())
                .addDeserializer(Double.class, new DeutschesDoubleDeserializer())
                .addDeserializer(Boolean.class, new BooleanDeserializer());

        mapper = new XmlMapper();
        mapper.registerModule(unmarshalling);
        mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<ProductData> parse(URL url) {
        try {
            return toList(mapper.readValue(url, ProductListData.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductData> parse(String xml) {
        try {
            return toList(mapper.readValue(xml, ProductListData.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<ProductData> toList(ProductListData data) {
        return Arrays.stream(data.products()).toList();
    }
}
