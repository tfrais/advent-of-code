package cz.frais.aoc.year2024.day22

import cz.frais.aoc.AdventOfCodeDaySolution
import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.*

@Suppress("MagicNumber")
object Year2024Day22 : AdventOfCodeDaySolution {

    private val logger = KotlinLogging.logger {}

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

    fun findChangesPriceInPart2Sequence(changes: List<Int>, sequence: List<Part2SequenceElement>): Int? {
        val windowedSequence = sequence.map { it.priceChange }.windowed(4)
        val priceIndex = windowedSequence.indexOf(changes)
        return if (priceIndex != -1) sequence[priceIndex + 3].price else null
    }

    fun generateCombinations(range: IntRange, size: Int): List<List<Int>> =
        if (size == 0) listOf(emptyList())
        else range.flatMap { element ->
            generateCombinations(range, size - 1).map { combination ->
                listOf(element) + combination
            }
        }

    override fun computePart1(input: String): Long =
        input.lines().map { it.toLong() }
            .sumOf { nextSecretNumber(it, 2000) }

    override fun computePart2(input: String): Long {
        val inputSequences = input.lines().map { it.toLong() }.map { initialSecretNumber ->
            generatePart2Sequence(initialSecretNumber, 2000)
        }
        var mostBananas: Long = Long.MIN_VALUE
        for (changes in generateCombinations(-9..9, 4)) {
            if (changes.drop(2) == listOf(-9, -9)) logger.info { "$changes, $mostBananas" }
            val bananasSum = inputSequences.sumOf { sequence ->
                findChangesPriceInPart2Sequence(changes, sequence)?.toLong() ?: 0
            }
            if (bananasSum > mostBananas) mostBananas = bananasSum
        }
        return mostBananas
    }

}

private const val PRUNE_MODULO_OPERAND: Long = 16777216L
fun Long.mix(operand: Long) = xor(operand)
fun Long.prune() = this % PRUNE_MODULO_OPERAND
