package cz.frais.aoc.year2023.day07

import cz.frais.aoc.year2023.day07.Hand.Companion.CARDS_IN_HAND

internal class Parser {

    companion object {
        private val HAND_REGEX: Regex = """(\w)(\w)(\w)(\w)(\w)\s+(\d+)""".toRegex()
    }

    fun parseHand(rawHand: String): Hand {
        val matchResult = HAND_REGEX.find(rawHand)
        check(matchResult != null) {
            "Could not parse the hand: $rawHand"
        }
        return Hand(
            matchResult.groups.drop(1).take(CARDS_IN_HAND)
                .map { Card.fromChar(it!!.value.toCharArray().single()) }
                .toList(),
            matchResult.groups.last()!!.value.toInt()
        )
    }

}
