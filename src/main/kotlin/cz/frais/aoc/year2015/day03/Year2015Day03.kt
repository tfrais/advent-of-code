package cz.frais.aoc.year2015.day03

import cz.frais.aoc.AdventOfCodeDaySolution
import cz.frais.aoc.structures.Position

object Year2015Day03 : AdventOfCodeDaySolution {

    private fun visitedPositions(directions: List<Direction>): Set<Position> {
        val visitedPositions = mutableSetOf<Position>()
        var currentPosition = Position(0, 0)
        visitedPositions.add(currentPosition)
        directions.forEach { direction ->
            currentPosition = direction.vector.apply(currentPosition)
            visitedPositions.add(currentPosition)
        }
        return visitedPositions
    }

    override fun computePart1(input: String): Long {
        return visitedPositions(
            input.toCharArray().map { Direction.fromChar(it) }
        ).count().toLong()
    }

    private fun Int.isOdd() = this % 2 != 0

    override fun computePart2(input: String): Long {
        val fullDirections = input.toCharArray().map { Direction.fromChar(it) }

        return visitedPositions(
            fullDirections.filterIndexed { index, _ -> index.isOdd() }
        ).union(
            visitedPositions(
                fullDirections.filterIndexed { index, _ -> !index.isOdd() }
            )
        ).count().toLong()
    }

}
