package cz.frais.aoc.year2023.day07

data class Hand(
    val cards: List<Card>,
    val bid: Int,
) {

    companion object {
        const val CARDS_IN_HAND = 5

        val comparator: Comparator<Hand> = Comparator { hand1, hand2 ->
            val hand1TypeOrdinal = resolveTypeOrdinalWithJokers(hand1.cards)
            val hand2TypeOrdinal = resolveTypeOrdinalWithJokers(hand2.cards)

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

        private fun resolveTypeOrdinalWithJokers(cards: List<Card>): Int {
            return JokerExpander.expandJokers(cards)
                .maxOfOrNull { Type.resolveType(Hand(it, 0)).ordinal } ?: 0
        }
    }

    fun distribution(): List<Int> {
        return cards
            .groupBy { it }
            .map { it.value.size }
            .sortedDescending()
    }

}


