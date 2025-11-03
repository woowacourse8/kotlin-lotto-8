package lotto.model

import lotto.util.ErrorMessage
import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class WinningLottoTest {
    @Test
    fun `당첨 번호가 6개가 아니면 예외 발생`() {
        val givenList = listOf(1, 2, 3)
        val givenBonusNum = 7

        assertThatThrownBy {
            WinningLotto(givenList, givenBonusNum)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining(ErrorMessage.INCORRECT_LOTTO_SIZE.fullMessage)
    }

    @Test
    fun `당첨 번호에 중복이 있으면 예외 발생`() {
        val givenList = listOf(1, 2, 3, 4, 5, 5)
        val givenBonusNum = 7

        assertThatThrownBy {
            WinningLotto(givenList, givenBonusNum)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining(ErrorMessage.LOTTO_NUMBER_MUST_BE_UNIQUE.fullMessage)
    }

    @Test
    fun `당첨 번호가 범위를 벗어나면 예외 발생`() {
        val givenList = listOf(1, 2, 3, 4, 5, 46)
        val givenBonusNum = 7

        assertThatThrownBy {
            WinningLotto(givenList, givenBonusNum)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining(ErrorMessage.LOTTO_NUMBER_OUT_OF_RANGE.fullMessage)
    }

    @Test
    fun `보너스 번호가 범위를 벗어나면 예외 발생`() {
        val givenList = listOf(1, 2, 3, 4, 5, 6)
        val givenBonusNum = 46

        assertThatThrownBy {
            WinningLotto(givenList, givenBonusNum)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining(ErrorMessage.LOTTO_NUMBER_OUT_OF_RANGE.fullMessage)
    }

    @Test
    fun `보너스 번호가 당첨 번호와 중복되면 예외 발생`() {
        val givenList = listOf(1, 2, 3, 4, 5, 6)
        val givenBonusNum = 6

        assertThatThrownBy {
            WinningLotto(givenList, givenBonusNum)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining(ErrorMessage.DUPLICATE_BONUS_NUMBER.fullMessage)
    }

    @Test
    fun `모든 번호가 정상이면 객체 생성 성공`() {
        val givenList = listOf(1, 2, 3, 4, 5, 6)
        val givenBonusNum = 7

        assertThatCode { WinningLotto(givenList, givenBonusNum) }.doesNotThrowAnyException()
    }
}