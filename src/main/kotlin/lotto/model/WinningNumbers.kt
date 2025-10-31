package lotto.model

import lotto.util.ErrorMessage
import lotto.util.LottoConstants

data class WinningNumbers(val winningNumbers: List<Int>, val bonusNumber: Int) {
    init {
        require(winningNumbers.size == LottoConstants.LOTTO_SIZE) {
            ErrorMessage.INCORRECT_LOTTO_SIZE.fullMessage
        }

        require(winningNumbers.toSet().size == LottoConstants.LOTTO_SIZE) {
            ErrorMessage.LOTTO_NUMBER_MUST_BE_UNIQUE.fullMessage
        }

        require(winningNumbers.all { it in LottoConstants.MIN_NUM..LottoConstants.MAX_NUM }) {
            ErrorMessage.INCORRECT_LOTTO_SIZE.fullMessage
        }
    }
}