package example1;

public record NonEmptyString(String value) {
    public NonEmptyString {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value can't be null or empty!");
        }
    }
}

