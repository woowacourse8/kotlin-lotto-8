package lotto.port

import lotto.model.LottoPapers

interface OutputPort {
    fun printPurchaseGuide()
    fun printLottos(lottoPapaers: LottoPapers)
}