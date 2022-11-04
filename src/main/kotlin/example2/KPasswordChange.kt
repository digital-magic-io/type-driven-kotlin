package example2

data class KPasswordChange(
    val oldPassword: String,
    val newPassword: String,
    val newPasswordRepeat: String
) {
    init {
        takeUnless { oldPassword == newPassword } ?: throw IllegalArgumentException("New password must differ from old one!")
        takeUnless { newPassword != newPasswordRepeat } ?: throw IllegalArgumentException("New passwords doesn't match!")
    }
}
