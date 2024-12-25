package cz.frais.aoc.year2024.day22

import cz.frais.aoc.AdventOfCodeDaySolution

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

    override fun computePart1(input: String): Long =
        input.lines().map { it.toLong() }
            .sumOf { nextSecretNumber(it, 2000) }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}

private const val PRUNE_MODULO_OPERAND: Long = 16777216L
fun Long.mix(operand: Long) = xor(operand)
fun Long.prune() = this % PRUNE_MODULO_OPERAND
