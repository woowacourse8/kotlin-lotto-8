package lotto.model

import lotto.util.ErrorMessage
import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 번호가 6개가 아니면 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            Lotto(1, 2, 3, 4, 5, 6, 7)
        }
    }

    @Test
    fun `로또 번호에 중복이 있으면 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            Lotto(1, 2, 3, 4, 5, 5)
        }
    }

    @Test
    fun `로또 번호가 1_45 범위를 벗어나면 예외 발생`() {
        assertThatThrownBy {
            Lotto(1, 2, 3, 4, 5, 46)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining(ErrorMessage.LOTTO_NUMBER_OUT_OF_RANGE.fullMessage)
    }

    @Test
    fun `모든 규칙이 정상이면 객체 생성 성공`() {
        assertThatCode { Lotto(1, 2, 3, 4, 5, 6) }.doesNotThrowAnyException()
    }
}