package lotto.view

import camp.nextstep.edu.missionutils.Console
import lotto.port.InputPort
import lotto.util.ErrorMessage

object InputView : InputPort {
    override fun readInput(): String {
        return Console.readLine() ?: throw IllegalArgumentException(ErrorMessage.INPUT_IS_NULL_OR_EMPTY.fullMessage)
    }
}