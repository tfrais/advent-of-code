package cz.frais.aoc.year2015.day10

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day10 : AdventOfCodeDaySolution {

    fun countSameChars(input: String, firstIndex: Int): Int {
        var count = 1
        val char = input[firstIndex]
        while ((firstIndex + count) < input.length && input[firstIndex + count] == char) {
            count++
        }
        return count
    }

    fun expandPart1(input: String): String {
        var i = 0
        return buildString {
            while (i < input.length) {
                val count = countSameChars(input, i)
                append(count)
                append(input[i])
                i += count
            }
        }
    }

    override fun computePart1(input: String): Long {
        var result = input
        repeat(40) { result = expandPart1(result) }
        return result.length.toLong()
    }

    override fun computePart2(input: String): Long {
        var result = input
        repeat(50) { result = expandPart1(result) }
        return result.length.toLong()
    }

}
