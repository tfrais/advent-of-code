package cz.frais.aoc.year2023.day04

import cz.frais.aoc.AdventOfCodeDaySolution
import kotlin.math.pow

object Year2023Day04 : AdventOfCodeDaySolution {

    fun parseNumbersFromCard(rawNumbers: String): Set<Int> {
        return rawNumbers.trim()
            .replace(Regex("\\s+"), " ")
            .split(" ")
            .map { it.toInt() }.toSet()
    }

    fun parseCard(rawCard: String): Card {
        val parts = rawCard.split(":")
        val cardId = parts[0].removePrefix("Card ").trim().toInt()
        val numberParts = parts[1].split("|")
        return Card(cardId, parseNumbersFromCard(numberParts[0]), parseNumbersFromCard(numberParts[1]))
    }

    fun computeMatchingNumbers(card: Card): Int {
        return card.numbers.intersect(card.winningNumbers).size
    }

    fun computePart1(card: Card): Int {
        val intersectSize = computeMatchingNumbers(card)
        return if (intersectSize > 0) 2.0.pow(intersectSize - 1).toInt() else 0
    }

    fun computePart1(cards: List<Card>): Int {
        return cards.sumOf { computePart1(it) }
    }

    fun computePart2(cards: List<Card>): Int {
        val cardMap = cards.associateWith { 1 }.toSortedMap(compareBy { it.id })
        for (card in cardMap.keys) {
            val matchingNumbers = computeMatchingNumbers(card)
            for (i in (card.id + 1)..card.id + matchingNumbers) {
                val cardI = cardMap.entries.find { it.key.id == i }
                if (cardI != null) {
                    cardMap[cardI.key] = cardMap[card]?.let { cardMap[cardI.key]?.plus(it) }
                }
            }
        }
        return cardMap.values.sum()
    }

    override fun computePart1(input: String): Long {
        return computePart1(input.lines().map { parseCard(it) }).toLong()
    }

    override fun computePart2(input: String): Long {
        return computePart2(input.lines().map { parseCard(it) }).toLong()
    }

}
