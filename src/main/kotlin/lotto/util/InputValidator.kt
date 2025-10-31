package lotto.util

object InputValidator {
    fun validatePurchaseAmount(input: String) {
        val amount = input.toIntOrNull()
            ?: throw IllegalArgumentException(ErrorMessage.INPUT_NOT_NUMERIC.fullMessage)
        require(amount % LottoConstants.LOTTO_AMOUNT_UNIT == 0) { ErrorMessage.INVALID_AMOUNT_UNIT.fullMessage }
        require(amount / LottoConstants.LOTTO_AMOUNT_UNIT > 0) { ErrorMessage.INVALID_QUANTITY_NOT_MET.fullMessage }
    }
}