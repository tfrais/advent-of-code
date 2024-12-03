package cz.frais.aoc.year2015.day02

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day02 : AdventOfCodeDaySolution {

    private const val DIMENSIONS_SEPARATOR = "x"

    private fun computeWrapping(dimensions: Triple<Long, Long, Long>): Long {
        return 2 * dimensions.first * dimensions.second +
                2 * dimensions.second * dimensions.third +
                2 * dimensions.third * dimensions.first +
                dimensions.toList().sorted().take(2).let { it[0] * it[1] }
    }

    private fun computeRibbon(dimensions: Triple<Long, Long, Long>): Long {
        return dimensions.toList().sorted().take(2).let { 2 * it[0] + 2 * it[1] } +
                dimensions.toList().reduce { acc, number -> acc * number }
    }

    private fun inputToDimensionTriple(input: String) = input.split("\n")
        .map { it.split(DIMENSIONS_SEPARATOR).map { part -> part.toLong() } }
        .map { (l, w, h) -> Triple(l, w, h) }

    override fun computePart1(input: String): Long {
        return inputToDimensionTriple(input)
            .sumOf { computeWrapping(it) }
    }

    override fun computePart2(input: String): Long {
        return inputToDimensionTriple(input)
            .sumOf { computeRibbon(it) }
    }

}
