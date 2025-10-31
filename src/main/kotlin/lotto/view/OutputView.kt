package lotto.view

import lotto.model.LottoPapers
import lotto.port.OutputPort

object OutputView : OutputPort {
    const val PURCHASE_GUIDE = "구입금액을 입력해 주세요."
    const val PURCHASE_LOTTO_COUNT = "개를 구매했습니다."
    const val WINNIG_NUMBER_GUIDE = "당첨 번호를 입력해 주세요."

    override fun printPurchaseGuide() {
        println(PURCHASE_GUIDE)
    }

    override fun printLottos(lottoPapers: LottoPapers) {
        println("${lottoPapers.getLottoCount()}$PURCHASE_LOTTO_COUNT")
        lottoPapers.lottos.forEach { lotto ->
             println(lotto.getNumbers())
        }
    }

    override fun printWinningNumbersGuide() {
        println(WINNIG_NUMBER_GUIDE)
    }
}