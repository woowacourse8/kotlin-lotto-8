package lotto.service

import lotto.model.Lottos
import lotto.util.LottoConstants

class LottoService {
    fun purchaseLottos(amount: Int): Lottos {
        val lottoCount = calculateLottoCount(amount)
        return LottoGenerator.generateLotto(lottoCount)
    }

    private fun calculateLottoCount(amount: Int) = amount / LottoConstants.LOTTO_AMOUNT_UNIT
}