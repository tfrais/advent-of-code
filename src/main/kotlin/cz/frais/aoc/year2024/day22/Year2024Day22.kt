package cz.frais.aoc.year2024.day22

import cz.frais.aoc.AdventOfCodeDaySolution
import java.util.*

@Suppress("MagicNumber")
object Year2024Day22 : AdventOfCodeDaySolution {

    fun nextSecretNumber(secretNumber: Long, nTimes: Int): Long {
        var result = secretNumber
        repeat(nTimes) { result = nextSecretNumber(result) }
        return result
    }

    fun nextSecretNumber(secretNumber: Long): Long {
        // Calculate the result of multiplying the secret number by 64.
        // Then, mix this result into the secret number.
        // Finally, prune the secret number.
        var result = secretNumber.mix(64 * secretNumber).prune()
        // Calculate the result of dividing the secret number by 32. Round the result down to the nearest integer.
        // Then, mix this result into the secret number. Finally, prune the secret number.
        result = result.mix(result / 32).prune()
        // Calculate the result of multiplying the secret number by 2048. Then, mix this result into the secret number.
        // Finally, prune the secret number.
        return result.mix(result * 2048).prune()
    }

    data class Part2SequenceElement(val price: Int, val priceChange: Int)

    fun generatePart2Sequence(initialSecretNumber: Long, length: Int): List<Part2SequenceElement> {
        val resultList = LinkedList<Part2SequenceElement>()
        var currentSecretNumber = initialSecretNumber
        resultList.add(Part2SequenceElement((currentSecretNumber % 10).toInt(), Int.MIN_VALUE))
        repeat(length) {
            currentSecretNumber = nextSecretNumber(currentSecretNumber)
            val currentPrice = (currentSecretNumber % 10).toInt()
            resultList.add(
                Part2SequenceElement(
                    currentPrice,
                    currentPrice - resultList.last().price
                )
            )
        }
        return resultList.toList()
    }

    private fun generateChangesToPriceMap(sequence: List<Part2SequenceElement>): Map<List<Int>, Int> {
        val resultMap = mutableMapOf<List<Int>, Int>()
        sequence.windowed(4).forEach { window ->
            resultMap.computeIfAbsent(window.map { it.priceChange }) {
                window.last().price
            }
        }
        return resultMap.toMap()
    }

    override fun computePart1(input: String): Long =
        input.lines().map { it.toLong() }.sumOf { nextSecretNumber(it, 2000) }

    override fun computePart2(input: String): Long {
        val changesToPriceMap = input.lines()
            .map { line -> generatePart2Sequence(line.toLong(), 2000) }
            .map { sequence -> generateChangesToPriceMap(sequence) }

        var mostBananas: Long = Long.MIN_VALUE
        for (changes in changesToPriceMap.flatMap { it.keys }.distinct()) {
            val bananasSum = changesToPriceMap.sumOf { map -> map[changes]?.toLong() ?: 0 }
            if (bananasSum > mostBananas) mostBananas = bananasSum
        }
        return mostBananas
    }

}

private const val PRUNE_MODULO_OPERAND: Long = 16777216L
fun Long.mix(operand: Long) = xor(operand)
fun Long.prune() = this % PRUNE_MODULO_OPERAND
