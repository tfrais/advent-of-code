package cz.frais.aoc.year2024.day11

import cz.frais.aoc.AdventOfCodeDaySolution
import io.github.oshai.kotlinlogging.KotlinLogging
import java.math.BigInteger
import java.util.*

object Year2024Day11 : AdventOfCodeDaySolution {

    private const val NUMBER_OF_BLINKS_PART1 = 25
    private const val NUMBER_OF_BLINKS_PART2 = 75

    @Suppress("MagicNumber")
    private val MULTIPLIER_2024 = BigInteger.valueOf(2024)

    private val logger = KotlinLogging.logger {}

    fun blink(stones: List<BigInteger>): List<BigInteger> {
        val returnList = LinkedList<BigInteger>()
        for (stoneValue in stones) {
            when {
                stoneValue == BigInteger.ZERO -> returnList.add(BigInteger.ONE)

                stoneValue.toString().length % 2 == 0 -> {
                    val strValue = stoneValue.toString()
                    returnList.addAll(
                        listOf(
                            BigInteger(strValue.take(strValue.length / 2)),
                            BigInteger(strValue.drop(strValue.length / 2))
                        )
                    )
                }

                else -> returnList.add(stoneValue.multiply(MULTIPLIER_2024))
            }
        }
        return returnList.toList()
    }

    fun compute(stones: List<BigInteger>, numberOfBlinks: Int): List<BigInteger> {
        var result = stones
        repeat(numberOfBlinks) { stepIndex ->
            logger.info { "Performing step #${stepIndex}; number of stones: ${result.size}" }
            result = blink(result)
        }
        return result
    }

    override fun computePart1(input: String): Long {
        return compute(input.split(" ").map { BigInteger(it) }, NUMBER_OF_BLINKS_PART1)
            .size.toLong()
    }

    override fun computePart2(input: String): Long {
        return compute(input.split(" ").map { BigInteger(it) }, NUMBER_OF_BLINKS_PART2)
            .size.toLong()
    }

}
