package lotto.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGeneratorTest {
    @Test
    fun `요청된 개수만큼 로또를 정확히 생성`() {
        // given
        val count = 5

        // when
        val resultLotto = LottoGenerator.generateLotto(5)

        // then
        assertThat(resultLotto.lottos.size).isEqualTo(count)
    }

    @Test
    fun `요청 개수가 0이면 빈 리스트를 반환`() {
        // given
        val count = 0

        // when
        val resultLotto = LottoGenerator.generateLotto(0)

        // then
        assertThat(resultLotto.lottos).isEmpty()
    }
}