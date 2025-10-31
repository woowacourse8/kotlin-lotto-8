package lotto.util

enum class ErrorMessage(private val message: String) {
    INPUT_IS_NULL_OR_EMPTY("입력값이 비어있습니다."),
    INPUT_NOT_NUMERIC("입력값이 숫자가 아닙니다."),
    INVALID_AMOUNT_UNIT("입력한 금액 단위가 유효하지 않습니다."),
    INVALID_QUANTITY_NOT_MET("로또 구매 수량이 1개 미만입니다.");

    val fullMessage: String
        get() = "$PREFIX $message"

    companion object {
        private const val PREFIX = "[ERROR]"
    }
}