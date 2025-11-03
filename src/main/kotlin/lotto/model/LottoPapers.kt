package lotto.model

data class LottoPapers(val lottos: List<Lotto>) {
    constructor(vararg lottos: Lotto) : this(lottos.toList())

    fun getLottoCount() = lottos.size
}