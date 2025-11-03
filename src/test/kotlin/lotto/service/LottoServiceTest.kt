package lotto.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoServiceTest {
    val lottoService = LottoService()

    @Test
    fun `구매 금액만큼 정확한 개수의 로또를 생성`() {
        // given
        val amount = 8000
        val expectedCount = 8

        // when
        val resultLottoPapers = lottoService.purchaseLottos(amount)

        // then
        assertThat(resultLottoPapers.lottos.size).isEqualTo(expectedCount)
    }

    @Test
    fun `구매 금액이 0원이면 빈 리스트를 반환`() {
        // given
        val amount = 0

        // when
        val resultLottoPapers = lottoService.purchaseLottos(amount)

        // then
        assertThat(resultLottoPapers.lottos).isEmpty()
    }

    @Test
    fun `1000원 미만이면 빈 리스트를 반환`() {
        // given
        val amount = 800

        // when
        val resultLottoPapers = lottoService.purchaseLottos(amount)

        // then
        assertThat(resultLottoPapers.lottos).isEmpty()
    }
}