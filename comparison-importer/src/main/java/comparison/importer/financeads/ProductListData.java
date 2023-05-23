package comparison.importer.financeads;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "kreditkarterechner")
public record ProductListData(
        @JacksonXmlProperty(localName = "product")
        @JacksonXmlElementWrapper(useWrapping = false)
        ProductData[] products
) { }
