package example2;

public record PasswordChange(
    String oldPassword,
    String newPassword,
    String newPasswordRepeat
) {
    public PasswordChange {
        if (oldPassword.equals(newPassword)) {
            throw new IllegalArgumentException("New password must differ from old one!");
        }
        if (!newPassword.equals(newPasswordRepeat)) {
            throw new IllegalArgumentException("New passwords doesn't match!");
        }
    }
}
