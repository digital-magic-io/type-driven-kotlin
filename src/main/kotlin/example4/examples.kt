package example4


sealed class KEither<out A, out B> {
    data class Left<A>(val value: A): KEither<A, Nothing>()
    data class Right<B>(val value: B): KEither<Nothing, B>()
}

@JvmInline
value class KError(val value: String)

typealias Validated<T> = KEither<KError, T>
typealias Validator<T> = (T) -> Validated<T>

val emptyStringValidator: Validator<String> = { value ->
    value.takeUnless { value.isEmpty() } ?.let { KEither.Right(it) }
        ?: KEither.Left(KError("Value can't be empty!"))
}

@JvmInline
value class KNonEmptyString private constructor(val value: String) {
    companion object {
        fun of(value: String): Validated<String> = emptyStringValidator(value)
    }
}
