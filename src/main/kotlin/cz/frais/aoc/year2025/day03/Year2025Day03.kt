package cz.frais.aoc.year2025.day03

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2025Day03 : AdventOfCodeDaySolution {

    private const val PART1_DIGIT_COUNT = 2
    private const val PART2_DIGIT_COUNT = 12

    fun greedyAlgorithm(batteryBank: String, digitCount: Int): Long {
        val selectedDigits = mutableListOf<Int>()
        var windowStart = 0

        while (selectedDigits.size < digitCount) {
            val remainingDigitCount = digitCount - selectedDigits.size
            val windowEnd = batteryBank.length - remainingDigitCount + 1
            val windowString = batteryBank.substring(windowStart, windowEnd)
            val maxDigitChar = windowString.max()
            val maxDigitCharIndexInWindow = windowString.indexOf(maxDigitChar) // note: taking the leftmost
            selectedDigits.add(maxDigitChar.toString().toInt())
            windowStart += maxDigitCharIndexInWindow + 1
        }

        return selectedDigits.joinToString(separator = "").toLong()
    }

    override fun computePart1(input: String): Long =
        input.lines().sumOf { greedyAlgorithm(it, PART1_DIGIT_COUNT) }

    override fun computePart2(input: String): Long =
        input.lines().sumOf { greedyAlgorithm(it, PART2_DIGIT_COUNT) }

}