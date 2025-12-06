package cz.frais.aoc.year2025.day03

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2025Day03 : AdventOfCodeDaySolution {

    private const val PART2_DIGIT_COUNT = 12

    fun maximumJoltagePart1(batteryBank: String): Int {
        var max = 0;
        for (firstDigitIndex in batteryBank.indices) {
            for (secondDigitIndex in firstDigitIndex + 1 until batteryBank.length) {
                val number = 10 * batteryBank[firstDigitIndex].digitToInt() +
                        batteryBank[secondDigitIndex].digitToInt()
                if (number > max) {
                    max = number
                }
            }
        }
        return max;
    }

    fun maximumJoltagePart2(batteryBank: String): Long {
        val selectedDigits = mutableListOf<Pair<Int, Int>>()
        var currentStartIndex = 0

        while (selectedDigits.size < PART2_DIGIT_COUNT) {
            val digitsToSelect = PART2_DIGIT_COUNT - selectedDigits.size
            val windowEnd = batteryBank.length - digitsToSelect + 1
            val searchPrefix = batteryBank.substring(currentStartIndex, windowEnd)
            val maxDigitChar = searchPrefix.max()
            val nextDigitIndexInPrefix = searchPrefix.indexOf(maxDigitChar)
            val nextDigitOriginalIndex = currentStartIndex + nextDigitIndexInPrefix
            selectedDigits.add(Pair(maxDigitChar.toString().toInt(), nextDigitOriginalIndex))
            currentStartIndex = nextDigitOriginalIndex + 1
        }

        return selectedDigits.map { it.first }.joinToString(separator = "").toLong()
    }

    override fun computePart1(input: String): Long =
        input.lines().sumOf { maximumJoltagePart1(it) }.toLong()

    override fun computePart2(input: String): Long =
        input.lines().sumOf { maximumJoltagePart2(it) }

}