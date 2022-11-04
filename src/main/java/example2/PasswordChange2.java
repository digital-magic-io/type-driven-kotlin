package example2;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import static utils.ExceptionUtils.defaultErrorIf;
import static utils.ExceptionUtils.defaultErrorIfNot;

public record PasswordChange2(
    String oldPassword,
    String newPassword,
    String newPasswordRepeat
) {
    private final static Predicate<String> passwordFormatChecker =
        Pattern.compile("[a-zA-Z0-9]+").asMatchPredicate();
    private static void validatePassword(final String pass) {
        defaultErrorIf(pass, it -> it == null || it.isEmpty(), () -> "Password can't be null or empty!");
        defaultErrorIfNot(pass, it -> it.length() < 6 || it.length() > 16, () -> "Password length must be in range 6-16 symbols!");
        defaultErrorIfNot(pass, passwordFormatChecker, () -> "Password contains incorrect symbols!");
    };

    public PasswordChange2 {
        validatePassword(oldPassword);
        validatePassword(newPassword);
        validatePassword(newPasswordRepeat);
        defaultErrorIf(null, it -> oldPassword.equals(newPassword), () -> "New password must differ from old one!");
        defaultErrorIfNot(null, it -> newPassword.equals(newPasswordRepeat), () -> "New passwords doesn't match!");
    }
}
