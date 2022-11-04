package utils;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class ExceptionUtils {

    public static <T> void defaultErrorIf(T value, Predicate<T> predicate, Supplier<String> errorMessage) {
        if (predicate.test(value)) {
            throw new IllegalArgumentException(errorMessage.get());
        }
    }

    public static <T> void defaultErrorIfNot(T value, Predicate<T> predicate, Supplier<String> errorMessage) {
        defaultErrorIf(value, (v) -> !predicate.test(v), errorMessage);
    }

}
