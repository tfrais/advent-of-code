package cz.frais.aoc.year2025.day04

import cz.frais.aoc.AdventOfCodeDaySolution
import cz.frais.aoc.structures.Position
import cz.frais.aoc.structures.Table

object Year2025Day04 : AdventOfCodeDaySolution {

    private const val PAPER_ROLL_SYMBOL = '@'
    private const val EMPTY_SYMBOL = '.'
    private const val REACHABLE_PAPER_ROLL_THRESHOLD = 4

    fun reachablePaperRolls(grid: Table<Char>): List<Position> {
        val result = mutableListOf<Position>()
        for (y in 0 until grid.height()) {
            for (x in 0 until grid.width()) {
                val position = Position(x, y)
                if (grid.valueAt(position) == PAPER_ROLL_SYMBOL) {
                    val adjacentRolls = grid.adjacentPositions(position)
                        .map { grid.valueAt(it) }
                        .filter { it == PAPER_ROLL_SYMBOL }
                    if (adjacentRolls.size < REACHABLE_PAPER_ROLL_THRESHOLD) {
                        result.add(position)
                    }
                }
            }
        }
        return result
    }

    override fun computePart1(input: String): Long {
        val grid = Table(input) { it }
        return reachablePaperRolls(grid).size.toLong()
    }

    override fun computePart2(input: String): Long {
        var grid = Table(input) { it }
        var result = 0L
        do {
            val removableRolls = reachablePaperRolls(grid)
            result += removableRolls.size
            grid = grid.withReplacedValues(
                removableRolls.associateWith { EMPTY_SYMBOL }
            )
        } while (removableRolls.isNotEmpty())
        return result
    }

}