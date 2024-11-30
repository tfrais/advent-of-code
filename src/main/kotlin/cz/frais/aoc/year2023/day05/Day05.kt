package cz.frais.aoc.year2023.day05

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
private val parser = Parser()

internal fun calculate(
    almanac: Almanac,
    initialElementRanges: List<LongRange>
): Long {
    var min = Long.MAX_VALUE
    for (range in initialElementRanges) {
        logger.debug { "Starting processing range $range" }
        for (element in range) {
            if (element % 10000000L == 0L) {
                logger.debug { "Going through element $element. The current min is $min" }
            }
            val finalElement = almanac.getFinalElement(element)
            if (finalElement < min) {
                min = finalElement
            }
        }
        logger.debug { "Ending processing range $range. The current min is $min" }
    }

    return min
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
