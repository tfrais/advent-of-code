package cz.frais.aoc.year2015.day25

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day25 : AdventOfCodeDaySolution {

    override fun computePart1(input: String): Long {
        var value = 20151125L
        var row = 1
        var column = 1

        while (row != 2978 || column != 3083) {
            value = (value * 252533) % 33554393
            if (row == 1) {
                row = column + 1
                column = 1
            } else {
                row--
                column++
            }
        }

        return value
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}
