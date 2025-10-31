package lotto.service

import camp.nextstep.edu.missionutils.Randoms
import lotto.model.Lotto
import lotto.model.LottoPapers
import lotto.util.LottoConstants

object LottoGenerator {
    fun generateLotto(lottoCount: Int): LottoPapers {
        val lottos = List(lottoCount) {
            val numbers = Randoms.pickUniqueNumbersInRange(
                LottoConstants.MIN_NUM,
                LottoConstants.MAX_NUM,
                LottoConstants.LOTTO_SIZE
            ).sorted()

            Lotto(numbers)
        }

        return LottoPapers(lottos)
    }
}