package lotto.model

data class LottoResult(
    val statistics: Map<Rank, Int>,
    val profitRate: Double
)
