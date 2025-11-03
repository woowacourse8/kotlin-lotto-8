package lotto.controller

import lotto.model.LottoResult
import lotto.model.Lottos
import lotto.port.InputPort
import lotto.port.OutputPort
import lotto.util.ErrorMessage
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StubInputPort(inputs: List<String>) : InputPort {
    private val inputQueue = ArrayDeque(inputs)

    override fun readInput(): String {
        return inputQueue.removeFirst()
    }
}

class StubOutputPort : OutputPort {
    // 최종 결과 기록
    var printedLottos: Lottos? = null
    var printedResult: LottoResult? = null
    var printedLastError: String? = null

    // 가이드 호출 횟수 기록
    var purchaseGuideCount = 0
    var winningNumbersGuideCount = 0
    var bonusNumberGuideCount = 0

    override fun printPurchaseGuide() {
        purchaseGuideCount++
    }

    override fun printLottos(lottos: Lottos) {
        this.printedLottos = lottos
    }

    override fun printWinningNumbersGuide() {
        winningNumbersGuideCount++
    }

    override fun printBonusNumberGuide() {
        bonusNumberGuideCount++
    }

    override fun printLottoStatistics(lottoResult: LottoResult) {
        this.printedResult = lottoResult
    }

    override fun printError(message: String?) {
        this.printedLastError = message
    }

}

class LottoControllerTest {
    @Test
    fun `모든 입력이 정상일 경우 전체 게임이 성공적으로 실행`() {
        // given
        val inputs = listOf(
            "1000",        // 1. 구매 금액
            "1,2,3,4,5,6", // 2. 당첨 번호
            "7"            // 3. 보너스 번호
        )
        val stubInput = StubInputPort(inputs)
        val stubOutput = StubOutputPort()

        val controller = LottoController(stubInput, stubOutput)

        // when
        controller.run()

        // then
        // 1. 에러가 발생하지 않았는지
        assertThat(stubOutput.printedLastError).isNull()

        // 2. 로또가 한 장 생성되었는지
        assertThat(stubOutput.printedLottos).isNotNull()
        assertThat(stubOutput.printedLottos?.lottos).hasSize(1)

        // 3. 통계가 정상적으로 출력되었는지
        assertThat(stubOutput.printedResult).isNotNull()

        // 4. 구매 가이드가 정확히 1번 호출되었는지
        assertThat(stubOutput.purchaseGuideCount).isEqualTo(1)
        assertThat(stubOutput.winningNumbersGuideCount).isEqualTo(1)
        assertThat(stubOutput.bonusNumberGuideCount).isEqualTo(1)
    }

    @Test
    fun `구매 금액 입력 실패 시 예외 메시지 출력 후 재입력 받음`() {
        // given
        val inputs = listOf(
            "천원",        // 1. 구매 금액 실패
            "1000",        // 2. 구매 금액 성공
            "1,2,3,4,5,6", // 3. 당첨 번호
            "7"            // 4. 보너스 번호
        )
        val stubInput = StubInputPort(inputs)
        val stubOutput = StubOutputPort()

        val controller = LottoController(stubInput, stubOutput)

        // when
        controller.run()

        // then
        // 1. 에러가 정확히 발생했는지
        assertThat(stubOutput.printedLastError).contains(ErrorMessage.INVALID_NUMBER_FORMAT.fullMessage)

        // 2. 구매 가이드가 2번 호출되었는지
        assertThat(stubOutput.purchaseGuideCount).isEqualTo(2)

        // 3. 결국 1000원으로 1장 구매에 성공했는지
        assertThat(stubOutput.printedLottos?.lottos).hasSize(1)
        assertThat(stubOutput.printedResult).isNotNull()
    }

    @Test
    fun `보너스 번호 오류시 당첨 번호 입력은 다시 요청하지 않음`() {
        // given
        val inputs = listOf(
            "1000",        // 1. 구매 금액
            "1,2,3,4,5,6", // 2. 당첨 번호
            "6",           // 3. 보너스 번호 실패
            "7"            // 4. 보너스 번호 성공
        )
        val stubInput = StubInputPort(inputs)
        val stubOutput = StubOutputPort()

        val controller = LottoController(stubInput, stubOutput)

        // when
        controller.run()

        // then
        // 구매 가이드가 당첨 번호 입력 1번, 보너스 번호 입력 2번 호출됐는지
        assertThat(stubOutput.winningNumbersGuideCount).isEqualTo(1)
        assertThat(stubOutput.bonusNumberGuideCount).isEqualTo(2)
    }
}