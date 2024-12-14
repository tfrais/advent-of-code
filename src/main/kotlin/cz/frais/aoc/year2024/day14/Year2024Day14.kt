package cz.frais.aoc.year2024.day14

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2024Day14 : AdventOfCodeDaySolution {

    private const val AREA_WIDTH_PART1 = 101
    private const val AREA_HEIGHT_PART1 = 103
    private const val STEPS_PART1 = 100

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

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}
