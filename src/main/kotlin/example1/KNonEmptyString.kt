package example1

@JvmInline
value class KNonEmptyString(val value: String) {
    init {
        // Null check is not required
        takeUnless { value.isEmpty() } ?: throw IllegalArgumentException("Value can't be null or empty!")
    }
}
