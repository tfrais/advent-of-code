package cz.frais.aoc.year2023.day11

import cz.frais.aoc.Position

object SpaceParser {

    private const val GALAXY_SYMBOL = '#'

    fun parse(input: String): Space {
        val rawSpace: List<List<Char>> = input.split("\n")
            .map { line -> line.toCharArray().toList() }

        require(rawSpace.map { it.size }.distinct().size == 1) {
            "The input lines have various length"
        }

        val height = rawSpace.size
        val width = rawSpace.first().size
        val galaxies = mutableListOf<Position>()

        for (y in 0..<height) {
            for (x in 0..<width) {
                if (rawSpace[y][x] == GALAXY_SYMBOL) {
                    galaxies.add(Position(x, y))
                }
            }
        }

        return Space(galaxies.toList(), width, height)
    }

}
