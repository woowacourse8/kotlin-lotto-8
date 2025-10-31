package lotto.service

import lotto.model.*

object LottoStatisticsCalculator {
    fun calculate(
        amount: Int,
        lottoPapers: LottoPapers,
        winningLotto: WinningLotto
    ): LottoResult {
        val rankMap = mutableMapOf<Rank, Int>()

        lottoPapers.lottos.forEach { lotto ->
            val rank = calculateRank(lotto, winningLotto)
            rankMap[rank] = rankMap.getOrDefault(rank, 0) + 1
        }

        val profitRate = calculateProfitRate(amount, rankMap)

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

    private fun calculateProfitRate(amount: Int, rankMap: MutableMap<Rank, Int>): Double {
        val totalEarnings = calculateTotalEarnings(rankMap)
        val profitRate = totalEarnings * 100.0 / amount
        return String.format("%.2f", profitRate).toDouble()
    }

    private fun calculateTotalEarnings(rankMap: MutableMap<Rank, Int>): Long {
        var totalEarnings = 0L

        rankMap.forEach { (rank, count) ->
            totalEarnings += rank.prize * count
        }

        return totalEarnings
    }
}