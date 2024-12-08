package cz.frais.aoc.year2015.day06

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day06 : AdventOfCodeDaySolution {

    private const val GRID_SIDE_SIZE = 1000

    override fun computePart1(input: String): Long {
        val lightsGrid = LightsGrid(GRID_SIDE_SIZE, GRID_SIDE_SIZE)
        input.lines().forEach({
            InstructionParser.parse(it).applyToLightsGrid(lightsGrid)
        })
        return lightsGrid.countLightsOn().toLong()
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}
