package comparison.importer.financeads;

public enum Country {
    SPAIN("es"),
    GERMANY("de");

    private final String iso2;

    Country(String iso2) {
        this.iso2 = iso2;
    }

    public String iso2() {
        return iso2;
    }
}
