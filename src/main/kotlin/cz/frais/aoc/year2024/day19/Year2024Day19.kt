package cz.frais.aoc.year2024.day19

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2024Day19 : AdventOfCodeDaySolution {

    private val solvableCache = mutableMapOf<String, List<List<String>>>()

    fun compute(towelPatterns: List<String>, design: String): List<List<String>> {
        return solvableCache.getOrPut(design) {
            if (design.isEmpty()) return@getOrPut listOf(emptyList())

            towelPatterns.filter { design.startsWith(it) }
                .flatMap { towelPattern ->
                    compute(towelPatterns, design.drop(towelPattern.length)).map { subList ->
                        listOf(towelPattern) + subList
                    }
                }
        }
    }

    fun isSolvable(towelPatterns: List<String>, design: String): Boolean =
        compute(towelPatterns, design).isNotEmpty()

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
        return designs.sumOf { compute(towelPatterns, it).size }.toLong()
    }

}
