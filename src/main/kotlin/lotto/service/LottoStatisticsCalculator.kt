package lotto.service

import lotto.model.Lotto
import lotto.model.LottoPapers
import lotto.model.LottoResult
import lotto.model.Rank
import lotto.model.WinningLotto

object LottoStatisticsCalculator {
    fun calculate(
        lottoPapers: LottoPapers,
        winningLotto: WinningLotto
    ): LottoResult {
        val rankMap = mutableMapOf<Rank, Int>()
        val profitRate: Double

        lottoPapers.lottos.forEach { lotto ->
            val rank = calculateRank(lotto, winningLotto)
            rankMap[rank] = rankMap.getOrDefault(rank, 0) + 1
        }

        return LottoResult(rankMap, profitRate)
    }

    private fun calculateRank(
        lotto: Lotto,
        winningLotto: WinningLotto
    ): Rank {
        val matchCount = lotto.getNumbers().count { it in winningLotto.winningNumbers }
        val bonusMatch = winningLotto.bonusNumber in lotto.getNumbers()

        return Rank.of(matchCount, bonusMatch)
    }
}