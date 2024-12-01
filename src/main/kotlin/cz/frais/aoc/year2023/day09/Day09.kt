package cz.frais.aoc.year2023.day09

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

internal fun history(input: String): List<List<Int>> {
    val history = mutableListOf<List<Int>>()
    history.add(input.split(" ").map { it.toInt() })
    while (history.last().any { it != 0 }) {
        history.add(history.last().zipWithNext { a, b -> b - a })
    }
    return history
}

internal fun extrapolate(input: String): Int {
    return history(input).sumOf { it.last() }
}

internal fun extrapolateBackwards(input: String): Int {
    val historyFirsts = history(input).map { it.first() }
    return historyFirsts.dropLast(1).foldRight(historyFirsts.last()) { current, acc -> current - acc }
}

fun main() {
    val content = object {}.javaClass.getResource("/2023/day09_input.txt")!!.readText()
    val resultPart1 = content.lines().sumOf { extrapolate(it) }
    logger.info { "Part 1 result is $resultPart1." }

    val resultPart2 = content.lines().sumOf { extrapolateBackwards(it) }
    logger.info { "Part 2 result is $resultPart2." }
}

