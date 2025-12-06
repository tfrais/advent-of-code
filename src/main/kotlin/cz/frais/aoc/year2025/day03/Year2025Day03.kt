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

    fun produceNumber(batteryBank: String, indices: List<Int>): Long {
        var place = 1L;
        var result = 0L;
        for (digitIndex in indices.reversed()) {
            result += place * batteryBank[digitIndex].digitToInt()
            place *= 10
        }
        return result
    }

    fun maximumJoltagePart2(batteryBank: String): Long {
        var max = 0L
        val batteryBankLength = batteryBank.length
        val indices = (0 until PART2_DIGIT_COUNT).toMutableList()

        while (indices[0] < batteryBankLength - PART2_DIGIT_COUNT) {

            val number = produceNumber(batteryBank, indices)
            if (number > max) {
                max = number
            }

            for (i in (PART2_DIGIT_COUNT - 1) downTo 0) {
                if (indices[i] < batteryBankLength - PART2_DIGIT_COUNT + i) {
                    indices[i] = indices[i] + 1
                    // reset all subsequent indices
                    for (j in i + 1 until PART2_DIGIT_COUNT) {
                        indices[j] = indices[j - 1] + 1
                    }
                    break
                }
            }

        }

        return max
    }

    override fun computePart1(input: String): Long =
        input.lines().sumOf { maximumJoltagePart1(it) }.toLong()

    override fun computePart2(input: String): Long =
        input.lines().sumOf { maximumJoltagePart2(it) }

}