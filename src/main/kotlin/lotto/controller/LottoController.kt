package lotto.controller

import lotto.port.InputPort
import lotto.port.OutputPort

class LottoController(
    private val inputPort: InputPort,
    private val outputPort: OutputPort
) {
    fun run() {
        outputPort.printPurchaseGuide()
        inputPort.readInput()
    }
}