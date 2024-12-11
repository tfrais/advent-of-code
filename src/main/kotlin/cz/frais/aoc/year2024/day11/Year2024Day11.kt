package cz.frais.aoc.year2024.day11

import cz.frais.aoc.AdventOfCodeDaySolution
import java.math.BigInteger

object Year2024Day11 : AdventOfCodeDaySolution {

    private const val NUMBER_OF_BLINKS_PART1 = 25
    private const val NUMBER_OF_BLINKS_PART2 = 75

    @Suppress("MagicNumber")
    private val MULTIPLIER_2024 = BigInteger.valueOf(2024)

    private val cache: MutableMap<Pair<BigInteger, Int>, Long> = mutableMapOf()

    private fun nextValues(value: BigInteger): List<BigInteger> =
        when {
            value == BigInteger.ZERO -> listOf(BigInteger.ONE)

            value.toString().length % 2 == 0 -> {
                val strValue = value.toString()
                listOf(
                    BigInteger(strValue.take(strValue.length / 2)),
                    BigInteger(strValue.drop(strValue.length / 2))
                )
            }

            else -> listOf(value.multiply(MULTIPLIER_2024))
        }

    fun compute(input: BigInteger, steps: Int): Long =
        cache.getOrPut(input to steps) {
            if (steps == 1) nextValues(input).size.toLong()
            else nextValues(input).sumOf { compute(it, steps - 1) }
        }

    override fun computePart1(input: String): Long {
        return input.split(" ")
            .sumOf { compute(BigInteger(it), NUMBER_OF_BLINKS_PART1) }
    }

    override fun computePart2(input: String): Long {
        return input.split(" ")
            .sumOf { compute(BigInteger(it), NUMBER_OF_BLINKS_PART2) }
    }

}
