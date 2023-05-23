package comparison.core.domain;

import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static comparison.core.domain.InfoTestBuilder.anInfo;

@Accessors(fluent = true)
public class ProductTestBuilder {
    private static int SEC_NUM = 0;

    @Setter private String id = UUID.randomUUID().toString();
    @Setter private int providerId = SEC_NUM++;
    @Setter private int providerProductId = SEC_NUM++;
    @Setter private Info info = anInfo().build();
    @Setter private InfoEdit infoEdit = null;
    @Setter private List<Flag> flags = new ArrayList<>();

    public static ProductTestBuilder aProduct() {
        return new ProductTestBuilder();
    }

    public Product build() {
        return new Product(
                id,
                providerId,
                providerProductId,
                info,
                infoEdit,
                flags
        );
    }
}
