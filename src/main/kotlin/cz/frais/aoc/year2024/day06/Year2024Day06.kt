package cz.frais.aoc.year2024.day06

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2024Day06 : AdventOfCodeDaySolution {

    override fun computePart1(input: String): Long {
        val mapWalkthrough = MapWalkthrough(input)
        check(mapWalkthrough.moveTillEndOrLoop() != MapWalkthrough.MoveResult.LOOP) {
            "No loops expected in part1"
        }
        return mapWalkthrough.visitedPositionDirection.map { it.first }.distinct().size.toLong()
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}
