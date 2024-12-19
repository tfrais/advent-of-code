package cz.frais.aoc.year2024.day19

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2024Day19 : AdventOfCodeDaySolution {

    private val solvableCache = mutableMapOf<String, Long>()

    fun compute(towelPatterns: List<String>, design: String): Long =
        solvableCache.getOrPut(design) {
            if (design.isEmpty()) 1L
            else towelPatterns.filter { design.startsWith(it) }
                .sumOf { towelPattern ->
                    compute(towelPatterns, design.drop(towelPattern.length))
                }
        }

    fun isSolvable(towelPatterns: List<String>, design: String): Boolean =
        compute(towelPatterns, design) > 0

    override fun computePart1(input: String): Long {
        solvableCache.clear()
        val lines = input.lines()
        val towelPatterns = lines.first().split(",").map { it.trim() }
        val designs = lines.drop(2)
        return designs.count { isSolvable(towelPatterns, it) }.toLong()
    }

    override fun computePart2(input: String): Long {
        solvableCache.clear()
        val lines = input.lines()
        val towelPatterns = lines.first().split(",").map { it.trim() }
        val designs = lines.drop(2)
        return designs.sumOf { compute(towelPatterns, it) }
    }

}
