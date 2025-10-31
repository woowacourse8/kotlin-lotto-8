package lotto.model

data class LottoPapers(val lottos: List<Lotto>) {
    fun getLottoCount() = lottos.size
}