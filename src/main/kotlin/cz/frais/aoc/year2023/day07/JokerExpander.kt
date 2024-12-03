package cz.frais.aoc.year2023.day07

object JokerExpander {

    fun expandJokers(cards: List<Card>): List<List<Card>> {
        var expandedLists = listOf(listOf<Card>())

        for (card in cards) {
            expandedLists = if (card == Card.JOKER) {
                expandedLists.flatMap { partialList ->
                    Card.entries.filter { it != Card.JOKER }.map { replacement ->
                        partialList + replacement
                    }
                }
            } else {
                expandedLists.map { it + card }
            }
        }
        return expandedLists
    }

}
