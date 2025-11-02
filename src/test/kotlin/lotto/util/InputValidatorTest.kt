package lotto.util

import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class InputValidatorTest {
    @Test
    fun `구매금액이 1000원 단위가 아닌 경우 예외 발생`() {
        assertThatThrownBy {
            InputValidator.validatePurchaseAmount("1800")
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining(ErrorMessage.INVALID_AMOUNT_UNIT.fullMessage)
    }

    @Test
    fun `구매 금액이 숫자가 아닌 경우 예외 발생`() {
        assertThatThrownBy {
            InputValidator.validatePurchaseAmount("천원")
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining(ErrorMessage.INVALID_NUMBER_FORMAT.fullMessage)
    }

    @Test
    fun `구매 금액이 0원 이하일 경우 예외가 발생한다`() {
        assertThatThrownBy {
            InputValidator.validatePurchaseAmount("0")
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining(ErrorMessage.INVALID_QUANTITY_NOT_MET.fullMessage)
    }

    @Test
    fun `로또 입력에 빈 값이 포함된 경우 예외 발생`() {
        assertThatThrownBy {
            InputValidator.validateWinningNumbers("1,2,,4,5,6")
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining(ErrorMessage.INPUT_IS_NULL_OR_EMPTY.fullMessage)
    }

    @Test
    fun `로또 입력에 숫자가 아닌 값이 포함된 경우 예외 발생`() {
        assertThatThrownBy {
            InputValidator.validateWinningNumbers("1,2,three,4,5,6")
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining(ErrorMessage.INVALID_NUMBER_FORMAT.fullMessage)
    }

    @Test
    fun `보너스 번호가 숫자가 아닌 경우 예외 발생`() {
        assertThatThrownBy {
            InputValidator.validateBonusNumber("칠")
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining(ErrorMessage.INVALID_NUMBER_FORMAT.fullMessage)
    }

    @Test
    fun `정상 형식의 경우 예외 미발생`() {
        assertThatCode {
            InputValidator.validatePurchaseAmount("8000")
        }.doesNotThrowAnyException()

        assertThatCode {
            InputValidator.validateWinningNumbers("1,2,3,4,5,6")
        }.doesNotThrowAnyException()

        assertThatCode {
            InputValidator.validateBonusNumber("7")
        }.doesNotThrowAnyException()
    }
}