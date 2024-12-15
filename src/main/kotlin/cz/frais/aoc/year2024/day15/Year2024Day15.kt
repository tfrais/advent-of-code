package cz.frais.aoc.year2024.day15

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2024Day15 : AdventOfCodeDaySolution {

    override fun computePart1(input: String): Long {
        val sokoban = Parser.parse(input)
        sokoban.moveTillEnd()
        return sokoban.computeGpsBoxes()
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}
