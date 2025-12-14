package cz.frais.aoc.year2015.day07

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day07 : AdventOfCodeDaySolution {

    override fun computePart1(input: String): Long {
        val unprocessedLines = input.lines().toMutableList()
        val wireValues = mutableMapOf<String, UShort>()
        while (unprocessedLines.isNotEmpty()) {
            for (line in unprocessedLines.toList()) {
                for (instruction in Instruction.entries) {
                    val matchResult = instruction.regex.find(line)
                    if (matchResult != null) {
                        val wasExecuted = instruction.operation(matchResult, wireValues)
                        if (wasExecuted) {
                            unprocessedLines.remove(line)
                            break
                        }
                    }
                }
            }
            wireValues["lx"]?.let { return it.toLong() }
        }
        throw IllegalStateException("Solution not found")
    }

    // manually replace "... -> b" input
    override fun computePart2(input: String): Long = computePart1(input)

}
