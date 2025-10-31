package lotto.model

enum class Rank(val matchCount: Int, val prize: Long, val needBonus: Boolean) {
    FIRST(6, 2_000_000_000L, false),
    SECOND(5, 30_000_000L, true),
    THIRD(5, 1_500_000L, false),
    FOURTH(4, 50_000L, false),
    FIFTH(3, 5_000L, false),
    MISS(0, 0L, false);

    companion object {
        fun of(matchCount: Int, matchBonus: Boolean): Rank {
            return entries.find { it.matchCount == matchCount && it.needBonus == matchBonus } ?: return MISS
        }
    }
}