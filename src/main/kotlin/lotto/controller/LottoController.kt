package lotto.controller

import lotto.model.WinningLotto
import lotto.port.InputPort
import lotto.port.OutputPort
import lotto.service.LottoService
import lotto.service.LottoStatisticsCalculator
import lotto.util.InputParser
import lotto.util.InputValidator

class LottoController(
    private val inputPort: InputPort,
    private val outputPort: OutputPort
) {
    fun run() {
        val amount = getValidPurchaseAmount()
        val lottoPapers = LottoService().purchaseLottos(amount)
        outputPort.printLottos(lottoPapers)

        val winningLotto = getValidWinningLotto()

        val lottoResult = LottoStatisticsCalculator.calculate(amount, lottoPapers, winningLotto)
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

    private fun getValidWinningLotto(): WinningLotto {
        while (true) {
            try {
                val winningNumbers = getValidWinningNumbersFormat()
                val bonusNumber = getValidBonusNumberFormat()

                return WinningLotto(winningNumbers, bonusNumber)

            } catch (e: IllegalArgumentException) {
                outputPort.printError(e.message)
            }
        }
    }

    private fun getValidWinningNumbersFormat(): List<Int> {
        outputPort.printWinningNumbersGuide()
        val winningNumberInput = inputPort.readInput()
        InputValidator.validateWinningNumbers(winningNumberInput)
        return InputParser.parseWinningNumbers(winningNumberInput)
    }

    private fun getValidBonusNumberFormat(): Int {
        outputPort.printBonusNumberGuide()
        val bonusNumberInput = inputPort.readInput()
        InputValidator.validateBonusNumber(bonusNumberInput)
        return InputParser.parseBonusNumber(bonusNumberInput)
    }
}