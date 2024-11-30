package cz.frais.aoc.year2023.day07

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
private val parser = Parser()

internal fun calculatePart1(hands: List<Hand>): Long {
    return hands
        .sortedWith(Hand.comparator)
        .mapIndexed { index, hand -> (index + 1L) * hand.bid }
        .sum()
}

fun main() {
    val content = object {}.javaClass.getResource("/2023/day07_input.txt")!!.readText()
    val resultPart1 = calculatePart1(content.lines().map { parser.parseHand(it) })

    logger.info { "Part 1 result is $resultPart1." }
}
