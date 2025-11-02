package lotto.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InputParserTest {
    @Test
    fun `구매 금액 문자열을 Int로 변환`() {
        val purchaseAmount = InputParser.parsePurchaseAmount("8000")
        assertThat(purchaseAmount).isEqualTo(8000)
    }

    @Test
    fun `로또 번호 입력 쉼표 분리 문자열을 List_Int로 변환`() {
        val winningNumbers = InputParser.parseWinningNumbers("1,2,3,4,5,6")
        assertThat(winningNumbers).isEqualTo(listOf(1,2,3,4,5,6))
    }

    @Test
    fun `보너스 번호 문자열을 Int로 변환`() {
        val bonusNumber = InputParser.parseBonusNumber("7")
        assertThat(bonusNumber).isEqualTo(7)
    }
}