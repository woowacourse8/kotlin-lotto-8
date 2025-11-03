package lotto.service

import lotto.model.Lottos
import lotto.model.WinningLotto
import lotto.util.LottoConstants

class LottoService {
    fun purchaseLottos(amount: Int): Lottos {
        val lottoCount = calculateLottoCount(amount)
        return LottoGenerator.generateLotto(lottoCount)
    }

    fun calculate(amount: Int, lottos: Lottos, winningLotto: WinningLotto) =
        LottoStatisticsCalculator.calculate(amount, lottos, winningLotto)

    private fun calculateLottoCount(amount: Int) = amount / LottoConstants.LOTTO_AMOUNT_UNIT
}
