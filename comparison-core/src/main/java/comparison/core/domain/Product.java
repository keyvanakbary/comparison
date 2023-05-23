package comparison.core.domain;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class Product {
    private String id;
    private int providerId;
    private int providerProductId;
    private Info info;
    private InfoEdit infoEdit;
    private List<Flag> flags;

    public String id() {
        return id;
    }

    public Info info() {
        return info;
    }

    public int providerId() {
        return providerId;
    }

    public int providerProductId() {
        return providerProductId;
    }

    public void updateInfo(Info info) {
        this.info = info;
    }

    public void editInfo(InfoEdit edit) {
        this.infoEdit = edit;
    }

    public InfoEdit infoEdit() {
        return infoEdit;
    }

    public Info infoEdited() {
        return info.merge(infoEdit);
    }

    public List<Flag> flags() {
        return flags;
    }

    public void addFlag(Flag flag) {
        flags.add(flag);
    }

    public void removeFlag(int index) {
        flags.remove(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
