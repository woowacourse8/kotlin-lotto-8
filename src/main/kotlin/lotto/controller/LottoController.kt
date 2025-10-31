package lotto.controller

import lotto.model.WinningNumbers
import lotto.port.InputPort
import lotto.port.OutputPort
import lotto.service.LottoService
import lotto.util.InputParser
import lotto.util.InputValidator

class LottoController(
    private val inputPort: InputPort,
    private val outputPort: OutputPort
) {
    fun run() {
        outputPort.printPurchaseGuide()
        val amountInput = inputPort.readInput()
        InputValidator.validatePurchaseAmount(amountInput)
        val amount = InputParser.parsePurchaseAmount(amountInput)
        val lottoPapers = LottoService().purchaseLottos(amount)
        outputPort.printLottos(lottoPapers)

        outputPort.printWinningNumbersGuide()
        val winningNumberInput = inputPort.readInput()
        InputValidator.validateWinningNumbers(winningNumberInput)
        val winningNumbers = InputParser.parseWinningNumbers(winningNumberInput)

        outputPort.printBonusNumberGuide()
        val bonusNumberInput = inputPort.readInput()
        InputValidator.validateBonusNumber(bonusNumberInput)
        val bonusNumber = InputParser.parseBonusNumber(bonusNumberInput)

        WinningNumbers(winningNumbers, bonusNumber)
    }
}