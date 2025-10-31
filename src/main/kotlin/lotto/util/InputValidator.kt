package lotto.util

object InputValidator {
    fun validatePurchaseAmount(input: String) {
        val amount = input.toIntOrNull()
            ?: throw IllegalArgumentException(ErrorMessage.INPUT_NOT_NUMERIC.fullMessage)

        require(amount % LottoConstants.LOTTO_AMOUNT_UNIT == 0) {
            ErrorMessage.INVALID_AMOUNT_UNIT.fullMessage
        }

        require(amount / LottoConstants.LOTTO_AMOUNT_UNIT > 0) {
            ErrorMessage.INVALID_QUANTITY_NOT_MET.fullMessage
        }
    }

    fun validateWinningNumbers(input: String) {
        val seenNumbers = mutableSetOf<Int>()

        input.split(LottoConstants.COMMA).forEach { number ->
            val number = number.toIntOrNull()
                ?: throw IllegalArgumentException(ErrorMessage.INPUT_NOT_NUMERIC.fullMessage)

            require(number < LottoConstants.MIN_NUM || number > LottoConstants.MAX_NUM) {
                ErrorMessage.LOTTO_NUMBER_OUT_OF_RANGE.fullMessage
            }

            require(seenNumbers.add(number)) {
                ErrorMessage.LOTTO_NUMBER_MUST_BE_UNIQUE.fullMessage
            }
        }

        require(seenNumbers.size == 6) {
            ErrorMessage.INCORRECT_LOTTO_SIZE.fullMessage
        }
    }
}