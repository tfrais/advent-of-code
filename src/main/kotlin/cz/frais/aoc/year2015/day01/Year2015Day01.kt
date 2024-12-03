package cz.frais.aoc.year2015.day01

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day01 : AdventOfCodeDaySolution {

    private const val CHAR_INCREMENT = '('
    private const val CHAR_DECREMENT = ')'

    private val CHAR_VALUE_MAP = mapOf(
        CHAR_INCREMENT to 1,
        CHAR_DECREMENT to -1
    )

    override fun computePart1(input: String): Long {
        return input.toCharArray().sumOf {
            CHAR_VALUE_MAP[it]?.toLong()
                ?: throw IllegalArgumentException("Key '$it' not found in CHAR_VALUE_MAP")
        }
    }

    override fun computePart2(input: String): Long {
        var floor = 0
        val charArray = input.toCharArray()
        for (i in charArray.indices) {
            floor += CHAR_VALUE_MAP[charArray[i]]
                ?: throw IllegalArgumentException("Key '${charArray[i]}' not found in CHAR_VALUE_MAP")
            if (floor < 0) {
                return (i + 1).toLong()
            }
        }
        error("There is no position leading to the basement")
    }

}
