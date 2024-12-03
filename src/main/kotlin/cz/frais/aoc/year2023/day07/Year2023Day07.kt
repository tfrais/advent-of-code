package cz.frais.aoc.year2023.day07

import cz.frais.aoc.AdventOfCodeDaySolution

private val parser = Parser()

object Year2023Day07 : AdventOfCodeDaySolution {

    fun computePart1(hands: List<Hand>): Long {
        return hands
            .sortedWith(Hand.comparator)
            .mapIndexed { index, hand -> (index + 1L) * hand.bid }
            .sum()
    }

    override fun computePart1(input: String): Long {
        return computePart1(input.lines().map { parser.parseHand(it) })
    }

    override fun computePart2(input: String): Long =
        computePart1(input)

}
