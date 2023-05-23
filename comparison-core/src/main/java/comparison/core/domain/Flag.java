package comparison.core.domain;

public record Flag (String description, FlagType type) {
    public static Flag positive(String description) {
        return new Flag(description, FlagType.POSITIVE);
    }

    public static Flag negative(String description) {
        return new Flag(description, FlagType.NEGATIVE);
    }
}
