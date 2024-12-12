package cz.frais.aoc.year2024.day12

import cz.frais.aoc.AdventOfCodeDaySolution
import cz.frais.aoc.structures.Position
import cz.frais.aoc.structures.Table
import cz.frais.aoc.structures.Vector
import cz.frais.aoc.structures.Vector.Companion.withNegativeVectors
import java.util.*

object Year2024Day12 : AdventOfCodeDaySolution {

    private val NEIGHBOUR_VECTORS = setOf(
        Vector(0, 1),
        Vector(1, 0)
    ).withNegativeVectors()

    private fun neighbourPositions(position: Position): Set<Position> =
        NEIGHBOUR_VECTORS.map { vector -> vector.apply(position) }.toSet()

    fun areaOfRegion(region: Set<Position>): Int = region.size

    fun perimeterOfRegion(region: Set<Position>): Int =
        region.sumOf { position ->
            (neighbourPositions(position) - region).size
        }

    fun findSide(
        region: Set<Position>,
        initialPosition: Position,
        moveVector: Vector,
        edgeDetectionVector: Vector,
    ): Set<Position> {
        val side = mutableSetOf<Position>()

        fun traverse(position: Position, vector: Vector) {
            var currentPosition = position
            while (currentPosition in region && edgeDetectionVector.apply(currentPosition) !in region) {
                side.add(currentPosition)
                currentPosition = vector.apply(currentPosition)
            }
        }

        traverse(initialPosition, moveVector)
        traverse(initialPosition, moveVector.negative())

        return side.toSet()
    }

    fun sidesOfRegion(region: Set<Position>): Int {
        val sides = mutableListOf<Set<Position>>()

        // (moveVector, edgeDetectionVector)
        val vectorCombinations = listOf(
            Vector(1, 0) to Vector(0, -1),
            Vector(1, 0) to Vector(0, 1),
            Vector(0, 1) to Vector(-1, 0),
            Vector(0, 1) to Vector(1, 0)
        )

        vectorCombinations.forEach { vectorCombination ->
            sides.addAll(
                region.map { findSide(region, it, vectorCombination.first, vectorCombination.second) }
                    .filter { it.isNotEmpty() }
                    .distinct()
            )
        }

        return sides.size
    }

    fun findFullRegion(initialPosition: Position, table: Table<Char>): Set<Position> {
        val region = mutableSetOf<Position>()
        val queue = LinkedList<Position>()
        val regionValue = table.valueAt(initialPosition)
        queue.add(initialPosition)
        while (queue.isNotEmpty()) {
            val position = queue.removeLast()
            region.add(position)
            neighbourPositions(position)
                .filter { table.inTable(it) }
                .filter { table.valueAt(it) == regionValue }
                .filter { it !in region }
                .forEach { queue.add(it) }
        }
        return region.toSet()
    }

    fun regionsOfTable(table: Table<Char>): Set<Set<Position>> {
        val regions = mutableSetOf<Set<Position>>()
        for (y in 0 until table.height()) {
            for (x in 0 until table.width()) {
                if (regions.none { region -> Position(x, y) in region }) {
                    regions.add(findFullRegion(Position(x, y), table))
                }
            }
        }
        return regions.toSet()
    }

    override fun computePart1(input: String): Long =
        regionsOfTable(Table(input) { it })
            .sumOf { areaOfRegion(it) * perimeterOfRegion(it) }.toLong()

    override fun computePart2(input: String): Long =
        regionsOfTable(Table(input) { it })
            .sumOf { areaOfRegion(it) * sidesOfRegion(it) }.toLong()

}
