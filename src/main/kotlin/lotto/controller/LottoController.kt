package lotto.controller

import lotto.model.Lotto
import lotto.model.WinningLotto
import lotto.port.InputPort
import lotto.port.OutputPort
import lotto.service.LottoService
import lotto.util.InputParser
import lotto.util.InputValidator

class LottoController(
    private val inputPort: InputPort,
    private val outputPort: OutputPort,
    private val lottoService: LottoService
) {
    fun run() {
        val amount = getValidPurchaseAmount()
        val lottos = lottoService.purchaseLottos(amount)
        outputPort.printLottos(lottos)

        val winningNumbers = getValidWinningNumbers()
        val winningLotto = getValidWinningLotto(winningNumbers)

        val lottoResult = lottoService.calculate(amount, lottos, winningLotto)
        outputPort.printLottoStatistics(lottoResult)
    }

    private fun getValidPurchaseAmount(): Int {
        while (true) {
            try {
                outputPort.printPurchaseGuide()
                val input = inputPort.readInput()
                InputValidator.validatePurchaseAmount(input)
                return InputParser.parsePurchaseAmount(input)
            } catch (e: IllegalArgumentException) {
                outputPort.printError(e.message)
            }
        }
    }

    private fun getValidWinningNumbers(): List<Int> {
        while (true) {
            try {
                outputPort.printWinningNumbersGuide()
                val input = inputPort.readInput()

                InputValidator.validateWinningNumbers(input)
                val numbers = InputParser.parseWinningNumbers(input)

                // 로또의 init 에도 검증 로직이 있음
                Lotto(numbers)

                return numbers

            } catch (e: IllegalArgumentException) {
                outputPort.printError(e.message)
            }
        }
    }

    private fun getValidWinningLotto(winningNumbers: List<Int>): WinningLotto {
        while (true) {
            try {
                outputPort.printBonusNumberGuide()
                val input = inputPort.readInput()

                InputValidator.validateBonusNumber(input)
                val bonusNumber = InputParser.parseBonusNumber(input)

                return WinningLotto(winningNumbers, bonusNumber)

            } catch (e: IllegalArgumentException) {
                outputPort.printError(e.message)
            }
        }
    }
}
