package cz.frais.aoc.year2023.day11

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
private const val PART2_EXPANSION_SIZE = 1000000

fun calculatePart1(space: Space, expansionSize: Int = 2): Long {
    val expandedSpace = space.expanded(expansionSize)
    val galaxyPairs = expandedSpace.galaxies.flatMapIndexed { index, galaxy ->
        expandedSpace.galaxies.drop(index + 1).map { otherGalaxy -> galaxy to otherGalaxy }
    }

    return galaxyPairs.sumOf {
        it.first.distanceTo(it.second).toLong()
    }
}

fun calculatePart2(space: Space, expansionSize: Int) =
    calculatePart1(space, expansionSize)

fun main() {
    val space = SpaceParser.parse(
        object {}.javaClass.getResource("/2023/day11_input.txt")!!.readText()
    )
    logger.info { "Part 1 result is ${calculatePart1(space)}." }
    logger.info { "Part 2 result is ${calculatePart2(space, PART2_EXPANSION_SIZE)}." }
}
