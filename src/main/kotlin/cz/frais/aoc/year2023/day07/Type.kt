package cz.frais.aoc.year2023.day07

@Suppress("MagicNumber")
internal enum class Type(val doesApply: (Hand) -> Boolean) {

    HIGH_CARD(
        { hand -> hand.distribution() == listOf(1, 1, 1, 1, 1) }
    ),
    ONE_PAIR(
        { hand -> hand.distribution() == listOf(2, 1, 1, 1) }
    ),
    TWO_PAIR(
        { hand -> hand.distribution() == listOf(2, 2, 1) }
    ),
    THREE_OF_KIND(
        { hand -> hand.distribution() == listOf(3, 1, 1) }
    ),
    FULL_HOUSE(
        { hand -> hand.distribution() == listOf(3, 2) }
    ),
    FOUR_OF_KIND(
        { hand -> hand.distribution() == listOf(4, 1) }
    ),
    FIVE_OF_KIND(
        { hand -> hand.distribution() == listOf(5) }
    );

    companion object {
        fun resolveType(hand: Hand): Type {
            return entries.singleOrNull { it.doesApply(hand) }
                ?: throw IllegalArgumentException("Could not resolve type: $hand")
        }
    }

}
