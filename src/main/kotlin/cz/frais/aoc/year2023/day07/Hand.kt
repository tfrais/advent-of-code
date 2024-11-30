package cz.frais.aoc.year2023.day07

internal data class Hand(
    val cards: List<Card>,
    val bid: Int
) {

    companion object {
        internal const val CARDS_IN_HAND = 5

        internal val comparator: Comparator<Hand> = Comparator { hand1, hand2 ->
            val hand1TypeOrdinal = Type.resolveType(hand1).ordinal
            val hand2TypeOrdinal = Type.resolveType(hand2).ordinal

            if (hand1TypeOrdinal != hand2TypeOrdinal) {
                hand1TypeOrdinal.compareTo(hand2TypeOrdinal)
            } else {
                for (i in hand1.cards.indices) {
                    val comparison = hand1.cards[i].ordinal.compareTo(hand2.cards[i].ordinal)
                    if (comparison != 0) return@Comparator comparison
                }
                hand1.bid.compareTo(hand2.bid)
            }
        }
    }

    fun distribution(): List<Int> {
        return cards
            .groupBy { it }
            .map { it.value.size }
            .sortedDescending()
    }

}


