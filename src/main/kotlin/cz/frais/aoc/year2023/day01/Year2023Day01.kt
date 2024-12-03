package cz.frais.aoc.year2023.day01

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2023Day01 : AdventOfCodeDaySolution {

    private const val TENS_ORDER = 10
    private val DIGIT_NAMES = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    fun translateDigitNames(line: String): String {
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

    fun calibrationValue(line: String): Int {
        val digits = Regex("""\d""").findAll(line).map { matchResult -> matchResult.value.toInt() }
        return TENS_ORDER * digits.first() + digits.last()
    }

    fun calibrationValue(lines: List<String>, translateDigitNames: Boolean): Long {
        return lines
            .map { if (translateDigitNames) translateDigitNames(it) else it }
            .sumOf { calibrationValue(it).toLong() }
    }

    override fun computePart1(input: String): Long =
        calibrationValue(input.lines(), false)


    override fun computePart2(input: String): Long =
        calibrationValue(input.lines(), true)

}
