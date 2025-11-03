package lotto.service

import lotto.model.Lotto
import lotto.model.LottoPapers
import lotto.model.Rank
import lotto.model.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoStatisticsCalculatorTest {

    private lateinit var defaultWinningLotto: WinningLotto

    @BeforeEach
    fun setUp() {
        // given
        defaultWinningLotto = WinningLotto(
            winningNumbers = listOf(1, 2, 3, 4, 5, 6),
            bonusNumber = 7
        )
    }

    @Test
    fun `5등 1개 당첨 시나리오`() {
        // given
        val amount = 1000
        val lotto5th = Lotto(1, 2, 3, 10, 11, 12)
        val lottoPapers = LottoPapers(lotto5th)

        // when
        val result = LottoStatisticsCalculator.calculate(amount, lottoPapers, defaultWinningLotto)

        // then
        assertThat(result.statistics).hasSize(1)
        assertThat(result.statistics).containsEntry(Rank.FIFTH, 1)

        assertThat(result.profitRate).isEqualTo(500.0)
    }

    @Test
    fun `당첨이 전혀 없는 시나리오`() {
        // given
        val amount = 2000
        val lottoMiss1 = Lotto(10, 11, 12, 13, 14, 15)
        val lottoMiss2 = Lotto(16, 17, 18, 19, 20, 21)
        val lottoPapers = LottoPapers(lottoMiss1, lottoMiss2)

        // when
        val result = LottoStatisticsCalculator.calculate(amount, lottoPapers, defaultWinningLotto)

        // then
        assertThat(result.statistics).hasSize(1)
        assertThat(result.statistics).containsEntry(Rank.MISS, 2)

        assertThat(result.profitRate).isEqualTo(0.0)
    }

    @Test
    fun `모든 등수가 1개씩 당첨되는 시나리오`() {
        // given
        val amount = 7000
        val lotto1st = Lotto(1, 2, 3, 4, 5, 6)
        val lotto2nd = Lotto(1, 2, 3, 4, 5, 7)
        val lotto3rd = Lotto(1, 2, 3, 4, 5, 8)
        val lotto4th = Lotto(1, 2, 3, 4, 8, 9)
        val lotto5th = Lotto(1, 2, 3, 8, 9, 10)
        val lottoMiss = Lotto(10, 11, 12, 13, 14, 15)
        val lottoPapers = LottoPapers(
            lotto1st, lotto2nd, lotto3rd, lotto4th, lotto5th, lottoMiss, lottoMiss
        )

        // when
        val result = LottoStatisticsCalculator.calculate(amount, lottoPapers, defaultWinningLotto)

        // then
        val expectedRankMap = mapOf(
            Rank.FIRST to 1,
            Rank.SECOND to 1,
            Rank.THIRD to 1,
            Rank.FOURTH to 1,
            Rank.FIFTH to 1,
            Rank.MISS to 2
        )
        assertThat(result.statistics).isEqualTo(expectedRankMap)

        val totalEarnings = Rank.FIRST.prize + Rank.SECOND.prize + Rank.THIRD.prize +
                Rank.FOURTH.prize + Rank.FIFTH.prize
        val expectedRate = String.format("%.2f", totalEarnings * 100.0 / amount).toDouble()
        assertThat(result.profitRate).isEqualTo(expectedRate)
    }

    @Test
    fun `수익률 계산 시 소수점 둘째자리까지 반올림`() {
        // given
        val amount = 3000
        val lotto5th = Lotto(1, 2, 3, 8, 9, 10)
        val lottoMiss = Lotto(10, 11, 12, 13, 14, 15)
        val lottoPapers = LottoPapers(lotto5th, lottoMiss, lottoMiss)

        // when
        val result = LottoStatisticsCalculator.calculate(amount, lottoPapers, defaultWinningLotto)

        // then
        val expectedRate = String.format("%.2f", Rank.FIFTH.prize * 100.0 / amount).toDouble()
        assertThat(result.profitRate).isEqualTo(expectedRate)
    }

    @Test
    fun `2등과 3등을 정확히 구분`() {
        // given
        val amount = 2000
        val lotto2nd = Lotto(1, 2, 3, 4, 5, 7)
        val lotto3rd = Lotto(1, 2, 3, 4, 5, 8)
        val lottoPapers = LottoPapers(lotto2nd, lotto3rd)

        // when
        val result = LottoStatisticsCalculator.calculate(amount, lottoPapers, defaultWinningLotto)

        // then
        assertThat(result.statistics).hasSize(2)
        assertThat(result.statistics).containsEntry(Rank.SECOND, 1)
        assertThat(result.statistics).containsEntry(Rank.THIRD, 1)
    }
}