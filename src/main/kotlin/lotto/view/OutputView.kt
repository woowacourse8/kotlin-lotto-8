package lotto.view

import lotto.port.OutputPort

object OutputView : OutputPort {
    const val PURCHASE_GUIDE = "구입금액을 입력해 주세요."

    override fun printPurchaseGuide() {
        println(PURCHASE_GUIDE)
    }
}