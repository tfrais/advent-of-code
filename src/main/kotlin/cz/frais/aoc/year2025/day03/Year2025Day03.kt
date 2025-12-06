package cz.frais.aoc.year2025.day03

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2025Day03 : AdventOfCodeDaySolution {

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

    override fun computePart1(input: String): Long =
        input.lines().sumOf { maximumJoltagePart1(it) }.toLong()

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}