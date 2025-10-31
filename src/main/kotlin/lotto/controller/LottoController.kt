package lotto.controller

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
        val input = inputPort.readInput()
        InputValidator.validatePurchaseAmount(input)
        val amount = InputParser.parsePurchaseAmount(input)
        val lottoPapers = LottoService().purchaseLottos(amount)
        outputPort.printLottos(lottoPapers)
    }
}