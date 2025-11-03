package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `6개 일치 시 FIRST 반환`() {
        assertThat(Rank.of(6, false)).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `5개 일치, 보너스 일치시 SECOND 반환`() {
        assertThat(Rank.of(5, true)).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `5개 일치, 보너스 불일치시 THIRD 반환`() {
        assertThat(Rank.of(5, false)).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `4개 일치시 FOURTH 반환`() {
        assertThat(Rank.of(4, false)).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `3개 일치시 FIFTH 반환`() {
        assertThat(Rank.of(3, false)).isEqualTo(Rank.FIFTH)
    }

    @Test
    fun `2개 이하 일치시 MISS 반환`() {
        assertThat(Rank.of(2, false)).isEqualTo(Rank.MISS)
    }
}