package lotto.port

import lotto.model.LottoPapers
import lotto.model.LottoResult
import lotto.model.WinningLotto

interface OutputPort {
    fun printPurchaseGuide()
    fun printLottos(lottoPapers: LottoPapers)
    fun printWinningNumbersGuide()
    fun printBonusNumberGuide()
    fun printLottoStatistics(lottoResult: LottoResult)
}