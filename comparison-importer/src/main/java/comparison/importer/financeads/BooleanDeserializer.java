package comparison.importer.financeads;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

class BooleanDeserializer extends JsonDeserializer<Boolean> {
    @Override
    public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.getValueAsString().equals("1")) {
            return Boolean.TRUE;
        }
        if (p.getValueAsString().equals("0")) {
            return Boolean.FALSE;
        }
        return null;
    }
}
