package lotto.util

object InputValidator {
    fun validatePurchaseAmount(input: String) {
        val amount = input.toIntOrNull()
            ?: throw IllegalArgumentException(ErrorMessage.INVALID_NUMBER_FORMAT.fullMessage)

        require(amount % LottoConstants.LOTTO_AMOUNT_UNIT == 0) {
            ErrorMessage.INVALID_AMOUNT_UNIT.fullMessage
        }

        require(amount / LottoConstants.LOTTO_AMOUNT_UNIT > 0) {
            ErrorMessage.INVALID_QUANTITY_NOT_MET.fullMessage
        }
    }

    fun validateWinningNumbers(input: String) {
        val numbers = input.split(LottoConstants.COMMA)

        require(numbers.none { it.isBlank() }) {
            ErrorMessage.INPUT_IS_NULL_OR_EMPTY.fullMessage
        }

        require(numbers.all { it.toIntOrNull() != null}) {
            ErrorMessage.INVALID_NUMBER_FORMAT.fullMessage
        }
    }
}