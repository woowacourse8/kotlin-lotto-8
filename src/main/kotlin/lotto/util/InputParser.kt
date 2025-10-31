package lotto.util

object InputParser {
    fun parsePurchaseAmount(input: String) = input.toInt()

    fun parseWinningNumbers(input: String) : MutableList<Int> {
        val winningNumbers = mutableListOf<Int>()

        input.split(LottoConstants.COMMA).forEach {
            winningNumbers.add(it.toInt())
        }

        return winningNumbers
    }
}