package cz.frais.aoc.year2024.day12

import cz.frais.aoc.AdventOfCodeDaySolution
import cz.frais.aoc.structures.Position
import cz.frais.aoc.structures.Vector

object Year2024Day12 : AdventOfCodeDaySolution {

    private val NEIGHBOUR_VECTORS = setOf(
        Vector(0, -1),
        Vector(0, 1),
        Vector(-1, 0),
        Vector(1, 0),
    )

    private fun neighbourPositions(position: Position): Set<Position> =
        NEIGHBOUR_VECTORS.map { vector -> vector.apply(position) }.toSet()

    fun areaOfRegion(region: Set<Position>): Int = region.size

    fun perimeterOfRegion(region: Set<Position>): Int =
        region.sumOf { position ->
            (neighbourPositions(position) - region).size
        }

    override fun computePart1(input: String): Long {
        TODO("Not yet implemented")
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}
