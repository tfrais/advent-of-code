package cz.frais.aoc.year2015.day18

import cz.frais.aoc.AdventOfCodeDaySolution
import cz.frais.aoc.structures.Position
import cz.frais.aoc.structures.Table

object Year2015Day18 : AdventOfCodeDaySolution {

    fun compute(originalGrid: Table<Boolean>, numberOfSteps: Int, fixedPosition: Set<Position>): Long {
        var grid = originalGrid.withReplacedValues(fixedPosition.associateWith { true })

        repeat(numberOfSteps) {
            val replacementMap = mutableMapOf<Position, Boolean>()
            for (x in 0 until grid.width()) {
                for (y in 0 until grid.height()) {
                    val position = Position(x, y)
                    if (position !in fixedPosition) {
                        val adjacentLights = grid.adjacentPositions(position).filter { grid.valueAt(it) }.size
                        if (grid.valueAt(position)) {
                            replacementMap[position] = adjacentLights in 2..3
                        } else {
                            replacementMap[position] = adjacentLights == 3
                        }
                    }
                }
            }
            grid = grid.withReplacedValues(replacementMap)
        }

        return grid.findValue(true).size.toLong()
    }

    fun computePart1(input: String, numberOfSteps: Int): Long {
        val grid = Table(input, { it == '#' })
        return compute(
            originalGrid = grid,
            numberOfSteps = numberOfSteps,
            fixedPosition = setOf()
        )
    }

    fun computePart2(input: String, numberOfSteps: Int): Long {
        val grid = Table(input, { it == '#' })
        return compute(
            originalGrid = grid,
            numberOfSteps = numberOfSteps,
            fixedPosition = setOf(
                Position(0, 0),
                Position(grid.width() - 1, 0),
                Position(0, grid.height() - 1),
                Position(grid.width() - 1, grid.height() - 1)
            )
        )
    }

    override fun computePart1(input: String) = computePart1(input, 100)

    override fun computePart2(input: String) = computePart2(input, 100)

}
