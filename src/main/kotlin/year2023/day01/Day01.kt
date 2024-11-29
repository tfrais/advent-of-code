package year2023.day01

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

fun calibrationValue(line: String): Int {
    val digits = Regex("""\d""").findAll(line).map { matchResult -> matchResult.value.toInt() }
    return 10 * digits.first() + digits.last()
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