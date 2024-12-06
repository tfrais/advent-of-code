package cz.frais.aoc.year2024.day06

import cz.frais.aoc.AdventOfCodeDaySolution
import cz.frais.aoc.structures.Position
import cz.frais.aoc.structures.Table

object Year2024Day06 : AdventOfCodeDaySolution {

    fun computePart1Positions(inputTable: Table<MapElement>): Set<Position> {
        val mapWalkthrough = MapWalkthrough(inputTable)
        check(mapWalkthrough.moveTillEndOrLoop() != MapWalkthrough.MoveResult.LOOP) {
            "No loops expected in part1"
        }
        return mapWalkthrough.visitedPositionDirection.map { it.first }.toSet()
    }

    override fun computePart1(input: String): Long {
        return computePart1Positions(Table(input) { MapElement.fromChar(it) })
            .size.toLong()
    }

    override fun computePart2(input: String): Long {
        var loops = 0L
        val originalTable: Table<MapElement> = Table(input) { MapElement.fromChar(it) }

        computePart1Positions(originalTable)
            .filter { originalTable.valueAt(it) == MapElement.EMPTY }
            .forEach { position ->
                val tableWithAddedObstacle = originalTable
                    .withReplacedValues(mapOf(position to MapElement.OBSTACLE))
                if (MapWalkthrough(tableWithAddedObstacle).moveTillEndOrLoop() ==
                    MapWalkthrough.MoveResult.LOOP
                ) {
                    loops++
                }
            }

        return loops
    }

}
