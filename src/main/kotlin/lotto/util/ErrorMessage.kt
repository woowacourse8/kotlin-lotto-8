package lotto.util

enum class ErrorMessage(private val message: String) {
    INPUT_IS_NULL_OR_EMPTY("입력값이 비어있습니다.");

    val fullMessage: String
        get() = "$PREFIX $message"

    companion object {
        private const val PREFIX = "[ERROR]"
    }
}