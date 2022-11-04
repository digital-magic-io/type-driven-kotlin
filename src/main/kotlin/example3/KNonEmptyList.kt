package example3

data class KNonEmptyList<T> private constructor(
    private val head: T,
    private val tail: List<T>
) :  List<T> by listOf(head) + tail {
    companion object {
        fun <T> of(value: T, vararg values: T): KNonEmptyList<T> =
            KNonEmptyList(value, listOf(*values))
    }
}
