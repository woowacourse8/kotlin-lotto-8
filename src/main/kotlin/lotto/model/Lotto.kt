package lotto.model

import lotto.util.ErrorMessage

data class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.size == 6) { ErrorMessage.INCORRECT_LOTTO_SIZE.fullMessage }
        require(numbers.toSet().size == 6) { ErrorMessage.LOTTO_NUMBER_MUST_BE_UNIQUE.fullMessage }
    }

    fun getNumbers() = numbers
}