package cz.frais.aoc.year2023.day01

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

private const val TENS_ORDER = 10
private val DIGIT_NAMES = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

internal fun translateDigitNames(line: String): String {
    var result = line
    var i = 0
    while (i < result.length) {
        var matched = false
        for ((index, digitName) in DIGIT_NAMES.withIndex()) {
            if (result.startsWith(digitName, i)) {
                result = result.substring(0, i) + index.toString() + result.substring(i + digitName.length)
                i += 1
                matched = true
            }
        }
        if (!matched) {
            i++
        }
    }
    return result
}

internal fun calibrationValue(line: String): Int {
    val digits = Regex("""\d""").findAll(translateDigitNames( line)).map { matchResult -> matchResult.value.toInt() }
    return TENS_ORDER * digits.first() + digits.last()
}

internal fun calibrationValue(lines: List<String>): Int {
    var result = 0
    lines.forEach { line -> result += calibrationValue(line) }
    return result
}

fun main() {
    val content = object {}.javaClass.getResource("/2023/day01_input.txt")!!.readText()
    logger.info { "Result is ${calibrationValue(content.lines())}." }
}
