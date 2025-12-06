package cz.frais.aoc.year2025.day02

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2025Day02 : AdventOfCodeDaySolution {

    fun parseRanges(input: String): List<LongRange> {
        return input.split(",")
            .map { rangeStr -> rangeStr.split("-").map { it.toLong() } }
            .map { it[0]..it[1] }
    }

    fun isValid(number: Long): Boolean {
        val charArray = number.toString().toCharArray()
        if (charArray.size % 2 != 0) {
            return true
        }
        for (i in 0 until charArray.size / 2) {
            if (charArray[i] != charArray[(charArray.size / 2) + i]) {
                return true
            }
        }
        return false
    }

    override fun computePart1(input: String): Long {
        var result = 0L
        for (range in parseRanges(input)) {
            for (number in range) {
                if (!isValid(number)) {
                    result += number
                }
            }
        }
        return result
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}