package lotto

import lotto.controller.LottoController
import lotto.service.LottoService
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    val inputView = InputView
    val outputView = OutputView
    val lottoService = LottoService()

    val lottoController = LottoController(
        inputPort = inputView,
        outputPort = outputView,
        lottoService = lottoService
    )

    lottoController.run()
}
