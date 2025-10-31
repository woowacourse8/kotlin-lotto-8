package lotto.model

import lotto.util.ErrorMessage

data class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.size == 6) { ErrorMessage.INCORRECT_LOTTO_SIZE.fullMessage }
    }

    // TODO: 추가 기능 구현
}