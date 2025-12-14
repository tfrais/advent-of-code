package cz.frais.aoc.year2025.day12

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2025Day12 : AdventOfCodeDaySolution {

    override fun computePart1(input: String): Long {
        val regionLines = input.split("\n\n").last().lines()
        var result = 0L
        for (line in regionLines) {
            val sizeQuantitySplit = line.split(":").map { it.trim() }
            val (regionWidth, regionHeight) = sizeQuantitySplit[0].split("x").map { it.toInt() }
            val shapeQuantitySum = sizeQuantitySplit[1].split(Regex("\\s+")).sumOf { it.toInt() }

            if ((regionWidth / 3) * (regionHeight / 3) >= shapeQuantitySum) {
                result++
            }
        }
        return result
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}