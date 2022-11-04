package example2

import utils.KExceptionUtils.defaultErrorIf
import utils.KExceptionUtils.defaultErrorIfNot

data class KPasswordChange3(
    val oldPassword: Password,
    val newPassword: Password,
    val newPasswordRepeat: Password
) {
    @JvmInline
    value class Password(val value: String) {
        companion object {
            private val passwordFormatChecker = "[a-zA-Z0-9]+".toRegex()::matches
        }
        init {
            value.defaultErrorIf({ "Password must not be empty" }) { it.isEmpty() }
                .defaultErrorIfNot({"Password length must be in range 6-16 symbols!" }) { (it.length in 6 .. 16) }
                .defaultErrorIfNot({"Password contains incorrect symbols!"}, passwordFormatChecker)
        }
    }

    init {
        defaultErrorIf({ "New password must differ from old one!" }) { oldPassword == newPassword }
        defaultErrorIfNot({ "New passwords doesn't match!" }) { newPassword == newPasswordRepeat }
    }
}
