package cz.frais.aoc.year2015.day05

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day05 : AdventOfCodeDaySolution {

    val RULES_PART1 = setOf(Rule.THREE_VOWELS, Rule.DOUBLE_CHAR, Rule.FORBIDDEN_SUBSTRINGS)
    val RULES_PART2 = setOf(Rule.TWO_CHARS_TWICE_NO_OVERLAP, Rule.TWO_SAME_CHARS_ANOTHER_IN_BETWEEN)

    fun isNice(string: String, rules: Set<Rule>): Boolean {
        return rules.all { rule -> rule.isNice(string) }
    }

    override fun computePart1(input: String): Long =
        input.lines().count { isNice(it, RULES_PART1) }.toLong()

    override fun computePart2(input: String): Long =
        input.lines().count { isNice(it, RULES_PART2) }.toLong()

}
