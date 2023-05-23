package comparison.importer.financeads;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

class DeutschesDoubleDeserializer extends JsonDeserializer<Double> {
    @Override
    public Double deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();

        if (value.equals("")) {
            return null;
        }

        NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
        try {
            return format.parse(value).doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
