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

    fun calculateMatchingNumbers(card: Card): Int {
        return card.numbers.intersect(card.winningNumbers).size
    }

    fun calculatePoints(card: Card): Int {
        val intersectSize = calculateMatchingNumbers(card)
        return if (intersectSize > 0) 2.0.pow(intersectSize - 1).toInt() else 0
    }

    fun calculatePoints(cards: List<Card>): Int {
        return cards.sumOf { calculatePoints(it) }
    }

    fun calculatePointsPart2(cards: List<Card>): Int {
        val cardMap = cards.associateWith { 1 }.toSortedMap(compareBy { it.id })
        for (card in cardMap.keys) {
            val matchingNumbers = calculateMatchingNumbers(card)
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
        return calculatePoints(input.lines().map { parseCard(it) }).toLong()
    }

    override fun computePart2(input: String): Long {
        return calculatePointsPart2(input.lines().map { parseCard(it) }).toLong()
    }

}
