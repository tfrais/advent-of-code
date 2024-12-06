package cz.frais.aoc.year2024.day06

import cz.frais.aoc.AdventOfCodeDaySolution
import cz.frais.aoc.structures.Position
import cz.frais.aoc.structures.Table

object Year2024Day06 : AdventOfCodeDaySolution {

    override fun computePart1(input: String): Long {
        val mapWalkthrough = MapWalkthrough(Table(input) { MapElement.fromChar(it) })
        check(mapWalkthrough.moveTillEndOrLoop() != MapWalkthrough.MoveResult.LOOP) {
            "No loops expected in part1"
        }
        return mapWalkthrough.visitedPositionDirection.map { it.first }.distinct().size.toLong()
    }

    override fun computePart2(input: String): Long {
        var loops = 0L
        val table: Table<MapElement> = Table(input) { MapElement.fromChar(it) }

        for (y in 0 until table.height()) {
            for (x in 0 until table.width()) {
                if (table.valueAt(Position(x, y)) == MapElement.EMPTY) {
                    val tableWithAddedObstacle = table
                        .withReplacedValues(mapOf(Position(x, y) to MapElement.OBSTACLE))
                    if (MapWalkthrough(tableWithAddedObstacle).moveTillEndOrLoop() ==
                        MapWalkthrough.MoveResult.LOOP
                    ) {
                        loops++
                    }
                }
            }
        }
        return loops
    }

}
