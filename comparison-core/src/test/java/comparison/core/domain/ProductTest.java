package comparison.core.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {
    @Test
    void itShouldAddFlag() {
        Product product = ProductTestBuilder.aProduct().build();
        Flag positiveFlag = Flag.positive("A positive thing");
        Flag negativeFlag = Flag.negative("A negative thing");

        product.addFlag(positiveFlag);
        product.addFlag(negativeFlag);

        List<Flag> flags = product.flags();
        assertEquals(List.of(positiveFlag, negativeFlag), flags);
    }

    @Test
    void itShouldRemoveFlag() {
        Product product = ProductTestBuilder.aProduct().flags(new ArrayList<>(Arrays.asList(
                Flag.positive("one"),
                Flag.positive("two"),
                Flag.positive("three")
        ))).build();

        product.removeFlag(1);

        List<Flag> flags = product.flags();
        assertEquals(List.of(Flag.positive("one"), Flag.positive("three")), flags);
    }

    @Test
    void itShouldReplaceInfoValues() {
        Info info = InfoTestBuilder.anInfo().extraInfo("Extra info").build();
        InfoEdit edit = new InfoEdit(
                "https://example.com",
                "https://example.com/logo.png",
                null,
                "Bank name",
                "A product",
                "Some requirements",
                "",
                ""
        );

        Info merge = info.merge(edit);

        assertEquals("https://example.com", merge.link());
        assertEquals("https://example.com/logo.png", merge.logo());
        assertEquals("Extra info", merge.extraInfo());
    }
}
