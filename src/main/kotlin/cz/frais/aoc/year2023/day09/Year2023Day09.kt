package cz.frais.aoc.year2023.day09

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2023Day09 : AdventOfCodeDaySolution {

    fun history(input: String): List<List<Int>> {
        val history = mutableListOf<List<Int>>()
        history.add(input.split(" ").map { it.toInt() })
        while (history.last().any { it != 0 }) {
            history.add(history.last().zipWithNext { a, b -> b - a })
        }
        return history
    }

    fun extrapolate(input: String): Int {
        return history(input).sumOf { it.last() }
    }

    fun extrapolateBackwards(input: String): Int {
        val historyFirsts = history(input).map { it.first() }
        return historyFirsts.dropLast(1).foldRight(historyFirsts.last()) { current, acc -> current - acc }
    }

    override fun computePart1(input: String): Long {
        return input.lines().sumOf { extrapolate(it) }.toLong()
    }

    override fun computePart2(input: String): Long {
        return input.lines().sumOf { extrapolateBackwards(it) }.toLong()
    }

}
