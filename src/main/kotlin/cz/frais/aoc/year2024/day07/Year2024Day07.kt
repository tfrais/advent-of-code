package cz.frais.aoc.year2024.day07

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2024Day07 : AdventOfCodeDaySolution {

    val ALLOWED_OPERATORS_PART1 = setOf(Operator.ADD, Operator.MULTIPLY)
    val ALLOWED_OPERATORS_PART2 = Operator.entries.toSet()

    private fun compute(input: String, allowedOperators: Set<Operator>): Long {
        return input.lines()
            .map { Equation.fromString(it, allowedOperators) }
            .filter { equation -> equation.operatorsSolvingEquation().isNotEmpty() }
            .sumOf { it.targetResult }
    }

    override fun computePart1(input: String): Long =
        compute(input, ALLOWED_OPERATORS_PART1)

    override fun computePart2(input: String): Long =
        compute(input, ALLOWED_OPERATORS_PART2)

}
