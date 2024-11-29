package cz.frais.aoc.year2023.day01

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

private const val TENS_ORDER = 10

fun calibrationValue(line: String): Int {
    val digits = Regex("""\d""").findAll(line).map { matchResult -> matchResult.value.toInt() }
    return TENS_ORDER * digits.first() + digits.last()
}

fun calibrationValue(lines: List<String>): Int {
    var result = 0
    lines.forEach { line -> result += calibrationValue(line) }
    return result
}

fun main() {
    val content = object {}.javaClass.getResource("/2023/day01_input.txt")!!.readText()
    logger.info { "Result is ${calibrationValue(content.lines())}." }
}
