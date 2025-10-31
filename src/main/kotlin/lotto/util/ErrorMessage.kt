package lotto.util

enum class ErrorMessage(private val message: String) {
    INPUT_IS_NULL_OR_EMPTY("입력값이 비어있습니다."),
    INVALID_NUMBER_FORMAT("숫자(정수)만 입력 가능합니다."),
    INVALID_AMOUNT_UNIT("입력한 금액 단위가 유효하지 않습니다."),
    INVALID_QUANTITY_NOT_MET("로또 구매 수량이 1개 미만입니다."),
    INCORRECT_LOTTO_SIZE("로또 번호는 6개여야 합니다."),
    LOTTO_NUMBER_OUT_OF_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    LOTTO_NUMBER_MUST_BE_UNIQUE("로또 번호는 중복될 수 없습니다."),
    DUPLICATE_BONUS_NUMBER("보너스 번호가 당첨 번호와 중복됩니다.");

    val fullMessage: String
        get() = "$PREFIX $message"

    companion object {
        private const val PREFIX = "[ERROR]"
    }
}