package cz.frais.aoc.year2024.day14

import cz.frais.aoc.AdventOfCodeDaySolution
import cz.frais.aoc.structures.Position
import cz.frais.aoc.structures.Vector
import java.io.File

object Year2024Day14 : AdventOfCodeDaySolution {

    private const val AREA_WIDTH_PART1 = 101
    private const val AREA_HEIGHT_PART1 = 103
    private const val STEPS_PART1 = 100

    private const val HORIZONTAL_VECTOR_MAX_PART2 = 7
    private const val MAX_STEPS_PART2 = 50000L

    fun compute(
        robots: List<Robot>,
        areaWidth: Int,
        areaHeight: Int,
        steps: Int,
    ): Long {
        robots.forEach { it.move(areaWidth, areaHeight, steps) }
        return robots.count { it.currentPosition.x < areaWidth / 2 && it.currentPosition.y < areaHeight / 2 }
            .toLong() *
                robots.count { it.currentPosition.x > areaWidth / 2 && it.currentPosition.y < areaHeight / 2 } *
                robots.count { it.currentPosition.x < areaWidth / 2 && it.currentPosition.y > areaHeight / 2 } *
                robots.count { it.currentPosition.x > areaWidth / 2 && it.currentPosition.y > areaHeight / 2 }
    }

    override fun computePart1(input: String): Long =
        compute(
            input.lines().map { Robot(it) },
            AREA_WIDTH_PART1,
            AREA_HEIGHT_PART1,
            STEPS_PART1
        )

    private fun writeToFile(
        positions: List<Position>,
        width: Int,
        height: Int,
        fileName: String,
    ) {
        val area = Array(height) { CharArray(width) { ' ' } }
        for ((x, y) in positions) {
            if (x in 0 until width && y in 0 until height) {
                area[y][x] = '#'
            }
        }
        File(fileName).writeText(
            area.joinToString("\n") { row -> row.joinToString("") }
        )
    }

    override fun computePart2(input: String): Long {
        val robots = input.lines().map { Robot(it) }
        val horizontalVectors = (1..HORIZONTAL_VECTOR_MAX_PART2).map { Vector(it, 0) }

        for (step in 1..MAX_STEPS_PART2) {
            robots.forEach { it.move(AREA_WIDTH_PART1, AREA_HEIGHT_PART1, 1) }
            val currentPositions = robots.map { r -> r.currentPosition }

            fun hasHorizontalNeighbours(robot: Robot): Boolean =
                horizontalVectors.map { v -> v.apply(robot.currentPosition) }
                    .all { pos -> pos in currentPositions }

            if (robots.any { robot -> hasHorizontalNeighbours(robot) }) {
                writeToFile(
                    robots.map { it.currentPosition },
                    AREA_WIDTH_PART1,
                    AREA_HEIGHT_PART1,
                    "C:\\Temp\\step$step.txt"
                )
                return step
            }
        }
        return -1
    }

}
