package cz.frais.aoc.year2024.day10

import cz.frais.aoc.AdventOfCodeDaySolution
import cz.frais.aoc.structures.Position
import cz.frais.aoc.structures.Table
import cz.frais.aoc.structures.Vector
import java.util.*

object Year2024Day10 : AdventOfCodeDaySolution {

    private const val TRAIL_STEPS = 10
    private val POSSIBLE_HIKE_VECTORS = setOf(
        Vector(0, -1),
        Vector(0, 1),
        Vector(-1, 0),
        Vector(1, 0),
    )

    private fun nextSteps(heightMap: Table<Int>, position: Position): List<Position> =
        POSSIBLE_HIKE_VECTORS
            .map { it.apply(position) }
            .filter { heightMap.inTable(it) }
            .filter { heightMap.valueAt(it) == heightMap.valueAt(position) + 1 }

    private fun computeTrails(heightMap: Table<Int>, initialPosition: Position): List<List<Position>> {
        val finishedTrails = mutableListOf<List<Position>>()
        val trailsInProgress = LinkedList<List<Position>>()
        trailsInProgress.add(listOf(initialPosition))

        while (trailsInProgress.isNotEmpty()) {
            val currentTrail = trailsInProgress.removeLast()

            val nextSteps = nextSteps(heightMap, currentTrail.last())
            if (nextSteps.isEmpty()) {
                finishedTrails.add(currentTrail)
            } else {
                nextSteps.forEach { neighbor ->
                    trailsInProgress.add(currentTrail + neighbor)
                }
            }
        }

        return finishedTrails.filter { it.size == TRAIL_STEPS }.toList()
    }

    override fun computePart1(input: String): Long {
        val heightMap = Table(input) { it.digitToInt() }
        return heightMap.findValue(0)
            .sumOf { computeTrails(heightMap, it).map { path -> path.last() }.distinct().size }.toLong()
    }

    override fun computePart2(input: String): Long {
        val heightMap = Table(input) { it.digitToInt() }
        return heightMap.findValue(0)
            .sumOf { computeTrails(heightMap, it).size }.toLong()
    }

}
