package cz.frais.aoc.year2024.day19

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2024Day19 : AdventOfCodeDaySolution {

    private val solvableCache = mutableMapOf<String, Boolean>()

    fun isSolvable(towelPatterns: List<String>, design: String): Boolean =
        solvableCache.getOrPut(design) {
            if (design.isEmpty() || design in towelPatterns) true
            else towelPatterns.any { towelPattern ->
                design.startsWith(towelPattern) && isSolvable(towelPatterns, design.drop(towelPattern.length))
            }
        }


    override fun computePart1(input: String): Long {
        solvableCache.clear()
        val lines = input.lines()
        val towelPatterns = lines.first().split(",").map { it.trim() }
        val designs = lines.drop(2)
        return designs.count { isSolvable(towelPatterns, it) }.toLong()
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}
