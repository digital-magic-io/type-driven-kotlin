package example2;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import static utils.ExceptionUtils.defaultErrorIf;
import static utils.ExceptionUtils.defaultErrorIfNot;

public record PasswordChange3(
    Password oldPassword,
    Password newPassword,
    Password newPasswordRepeat
) {
    public record Password(String value) {
        private final static Predicate<String> passwordFormatChecker =
            Pattern.compile("[a-zA-Z0-9]+").asMatchPredicate();

        public Password {
            defaultErrorIf(value, it -> it == null || it.isEmpty(), () -> "Password can't be null or empty!");
            defaultErrorIfNot(value, it -> it.length() < 6 || it.length() > 16, () -> "Password length must be in range 6-16 symbols!");
            defaultErrorIfNot(value, passwordFormatChecker, () -> "Password contains incorrect symbols!");
        }
    }

    public PasswordChange3 {
        defaultErrorIf(null, it -> oldPassword.equals(newPassword), () -> "New password must differ from old one!");
        defaultErrorIfNot(null, it -> newPassword.equals(newPasswordRepeat), () -> "New passwords doesn't match!");
    }
}
