package lotto.util

object InputParser {
    fun parsePurchaseAmount(input: String) = input.toInt()

    fun parseWinningNumbers(input: String): List<Int> {
        return input.split(LottoConstants.COMMA).map { it.toInt() }
    }
}