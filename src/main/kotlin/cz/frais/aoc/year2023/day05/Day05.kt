package cz.frais.aoc.year2023.day05

import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.stream.Collectors

private val logger = KotlinLogging.logger {}
private val parser = Parser()

internal fun calculate(almanac: Almanac, range: LongRange): Long {
    var min = Long.MAX_VALUE
    logger.debug { "Starting processing range $range" }
    for (element in range) {
        val finalElement = almanac.getFinalElement(element)
        if (finalElement < min) {
            min = finalElement
        }
    }
    logger.debug { "Ending processing range $range. Its min is $min" }
    return min
}

internal fun calculate(almanac: Almanac, initialElementRanges: List<LongRange>): Long {
    return initialElementRanges.parallelStream()
        .map { range -> calculate(almanac, range) }
        .collect(Collectors.minBy(Comparator.naturalOrder()))
        .orElse(0)
}

fun main() {
    val input = object {}.javaClass.getResource("/2023/day05_input.txt")!!.readText()

    val parserResultPart1 = parser.parse(input, false)
    val resultPart1 = calculate(parserResultPart1.almanac, parserResultPart1.initialElementRanges)
    logger.info { "Part 1 result is $resultPart1." }

    val parserResultPart2 = parser.parse(input, true)
    val resultPart2 = calculate(parserResultPart2.almanac, parserResultPart2.initialElementRanges)
    logger.info { "Part 2 result is $resultPart2." }
}
