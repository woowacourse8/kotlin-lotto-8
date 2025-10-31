package lotto.service

import lotto.util.LottoConstants

class LottoService {
    fun calculateLottoCount(amount: Int) = amount / LottoConstants.LOTTO_AMOUNT_UNIT
}