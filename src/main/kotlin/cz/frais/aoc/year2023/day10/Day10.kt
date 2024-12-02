package cz.frais.aoc.year2023.day10

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

internal fun calculatePart1(input: String): Int {
    return PipeCalculator.pipe(Plan(input)).size / 2
}

fun main() {
    val content = object {}.javaClass.getResource("/2023/day10_input.txt")!!.readText()
    val resultPart1 = calculatePart1(content)
    logger.info { "Part 1 result is $resultPart1." }
}
