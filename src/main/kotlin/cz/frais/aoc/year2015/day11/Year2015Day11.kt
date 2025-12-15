package cz.frais.aoc.year2015.day11

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day11 : AdventOfCodeDaySolution {

    fun nextChar(char: Char, distance: Int): Char = (char.code + distance).toChar()

    fun nextInSequence(input: String): String {
        var result = input.replace('i', 'j').replace('l', 'm').replace('o', 'p')
        if (result.last() in listOf('i', 'l', 'o')) {
            result = result.dropLast(1) + nextChar(result.last(), 2)
        } else if (result.last() == 'z') {
            result = nextInSequence(input.dropLast(1)) + 'a'
        } else {
            result = result.dropLast(1) + nextChar(result.last(), 1)
        }
        return result
    }

    fun hasStraightOfThreeLetters(input: String): Boolean {
        for (i in 0 until input.length - 2) {
            if (input[i].code == (input[i + 1].code - 1) && input[i].code == (input[i + 2].code - 2)) {
                return true
            }
        }
        return false
    }

    fun hasTwoPairsOfSameLetters(input: String): Boolean {
        var count = 0
        var i = 0
        while (i in 0 until input.length - 1) {
            if (input[i] == input[i + 1]) {
                count++
                i++
            }
            i++
        }
        return count >= 2
    }

    override fun computePart1(input: String): Long {
        var result = input
        while (!hasStraightOfThreeLetters(result) || !hasTwoPairsOfSameLetters(result)) {
            result = nextInSequence(result)
        }
        return result.length.toLong()
    }

    override fun computePart2(input: String): Long {
        var result = nextInSequence("hepxxyzz")
        while (!hasStraightOfThreeLetters(result) || !hasTwoPairsOfSameLetters(result)) {
            result = nextInSequence(result)
        }
        return result.length.toLong()
    }

}
