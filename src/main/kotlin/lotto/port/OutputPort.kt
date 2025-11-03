package lotto.port

import lotto.model.LottoResult
import lotto.model.Lottos

interface OutputPort {
    fun printPurchaseGuide()
    fun printLottos(lottos: Lottos)
    fun printWinningNumbersGuide()
    fun printBonusNumberGuide()
    fun printLottoStatistics(lottoResult: LottoResult)
    fun printError(message: String?)
}