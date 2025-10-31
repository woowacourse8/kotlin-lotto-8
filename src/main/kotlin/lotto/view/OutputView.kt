package lotto.view

import lotto.model.LottoPapers
import lotto.model.LottoResult
import lotto.model.Rank
import lotto.port.OutputPort

object OutputView : OutputPort {
    const val PURCHASE_GUIDE = "구입금액을 입력해 주세요."
    const val PURCHASE_LOTTO_COUNT = "개를 구매했습니다."
    const val WINNING_NUMBER_GUIDE = "당첨 번호를 입력해 주세요."
    const val BONUS_NUMBER_GUIDE = "보너스 번호를 입력해 주세요."
    const val WIN_STATISTICS = "당첨 통계"
    const val BAR_LINE = "---"

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
        println(WINNING_NUMBER_GUIDE)
    }

    override fun printBonusNumberGuide() {
        println(BONUS_NUMBER_GUIDE)
    }

    override fun printLottoStatistics(lottoResult: LottoResult) {
        println(WIN_STATISTICS)
        println(BAR_LINE)

        Rank.entries.reversed()
            .filter { it != Rank.MISS }
            .forEach { rank ->
                val accordCount = "${rank.matchCount}개 일치"
                val accordBonus = if (rank.needBonus) ", 보너스 볼 일치" else ""
                val prize = "(${String.format("%,d", rank.prize)}원)"
                val matchCount = lottoResult.statistics[rank] ?: 0

                println("$accordCount$accordBonus $prize - ${matchCount}개")
            }

        println("총 수익률은 ${lottoResult.profitRate}%입니다.")
    }

    override fun printError(message: String?) {
        println(message ?: "[ERROR] 알 수 없는 오류가 발생했습니다.")
    }
}
