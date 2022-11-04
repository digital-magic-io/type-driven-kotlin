package utils

object KExceptionUtils {

    fun <T> T.throwIf(predicate: (T) -> Boolean, ex: (T) -> Throwable): T =
        if (predicate(this)) throw ex(this) else this

    fun <T> T.defaultErrorIf(errorMessage: () -> String, predicate: (T) -> Boolean): T = throwIf(predicate) {
        IllegalArgumentException(errorMessage())
    }

    fun <T> T.defaultErrorIfNot(errorMessage: () -> String, predicate: (T) -> Boolean): T =
        defaultErrorIf(errorMessage) { !predicate(it) }
}
