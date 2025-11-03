package lotto.model

import lotto.util.ErrorMessage
import lotto.util.LottoConstants

data class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.size == 6) { ErrorMessage.INCORRECT_LOTTO_SIZE.fullMessage }
        require(numbers.toSet().size == 6) { ErrorMessage.LOTTO_NUMBER_MUST_BE_UNIQUE.fullMessage }
        require(numbers.all { it in LottoConstants.MIN_NUM..LottoConstants.MAX_NUM }) {
            ErrorMessage.LOTTO_NUMBER_OUT_OF_RANGE.fullMessage
        }
    }

    fun getNumbers() = numbers
}