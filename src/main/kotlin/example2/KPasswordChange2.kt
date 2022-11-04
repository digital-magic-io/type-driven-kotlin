package example2

import utils.KExceptionUtils.defaultErrorIf
import utils.KExceptionUtils.defaultErrorIfNot

data class KPasswordChange2(
    val oldPassword: String,
    val newPassword: String,
    val newPasswordRepeat: String
) {
    companion object {
        private val passwordFormatChecker = "[a-zA-Z0-9]+".toRegex()::matches

        fun String.validatePassword() =
            defaultErrorIf({ "Password must not be empty" }) { it.isEmpty() }
                .defaultErrorIfNot({"Password length must be in range 6-16 symbols!" }) { (it.length in 6 .. 16) }
                .defaultErrorIfNot({"Password contains incorrect symbols!"}, passwordFormatChecker)
    }

    init {
        oldPassword.validatePassword()
        newPassword.validatePassword()
        newPasswordRepeat.validatePassword()
        defaultErrorIf({ "New password must differ from old one!" }) { oldPassword == newPassword }
        defaultErrorIfNot({ "New passwords doesn't match!" }) { newPassword == newPasswordRepeat }
    }
}
