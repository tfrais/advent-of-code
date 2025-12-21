package cz.frais.aoc.year2015.day19

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day19 : AdventOfCodeDaySolution {

    private data class ParsingResult(val transitions: Set<Pair<String, String>>, val string: String)

    private fun parseInput(input: String) = ParsingResult(
        transitions = input.lines().dropLast(2).map { it.split(" => ") }.map { it[0] to it[1] }.toSet(),
        string = input.lines().last()
    )

    override fun computePart1(input: String): Long {
        val parsedInput = parseInput(input)
        val molecules = mutableSetOf<String>()

        for (i in parsedInput.string.indices) {
            for (entry in parsedInput.transitions) {
                if (i + entry.first.length - 1 < parsedInput.string.length && parsedInput.string.substring(i until i + entry.first.length) == entry.first) {
                    molecules.add(parsedInput.string.take(i) + entry.second + parsedInput.string.substring(i + entry.first.length))
                }
            }
        }

        return molecules.size.toLong()
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}
