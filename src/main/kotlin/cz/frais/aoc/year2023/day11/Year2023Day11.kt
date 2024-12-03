package cz.frais.aoc.year2023.day11

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2023Day11 : AdventOfCodeDaySolution {

    private const val PART2_EXPANSION_SIZE = 1000000

    fun computeDistanceSum(space: Space, expansionSize: Int = 2): Long {
        val expandedSpace = space.expanded(expansionSize)
        val galaxyPairs = expandedSpace.galaxies.flatMapIndexed { index, galaxy ->
            expandedSpace.galaxies.drop(index + 1).map { otherGalaxy -> galaxy to otherGalaxy }
        }

        return galaxyPairs.sumOf {
            it.first.distanceTo(it.second).toLong()
        }
    }

    override fun computePart1(input: String): Long {
        return computeDistanceSum(SpaceParser.parse(input))
    }

    override fun computePart2(input: String): Long {
        return computeDistanceSum(SpaceParser.parse(input), PART2_EXPANSION_SIZE)
    }

}
