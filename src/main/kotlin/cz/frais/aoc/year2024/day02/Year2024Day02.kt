package cz.frais.aoc.year2024.day02

import cz.frais.aoc.AdventOfCodeDaySolution
import kotlin.math.abs
import kotlin.math.sign

object Year2024Day02 : AdventOfCodeDaySolution {

    private const val DIFFERENCE_TOLERANCE_MIN = 1
    private const val DIFFERENCE_TOLERANCE_MAX = 3

    fun parse(input: String): List<List<Int>> {
        return input.lines().map { line -> line.split(" ").map { it.toInt() } }
    }

    fun isSafe(report: List<Int>): Boolean {
        val diffs = report.zipWithNext { a, b -> b - a }
        val absDiffs = diffs.map { abs(it) }
        return absDiffs.min() >= DIFFERENCE_TOLERANCE_MIN && absDiffs.max() <= DIFFERENCE_TOLERANCE_MAX
                && diffs.map { sign(it.toDouble()) }.distinct().size == 1
    }

    fun isSafeAfterRemovingElement(report: List<Int>): Boolean {
        return report.indices.any { i ->
            isSafe(report.filterIndexed { index, _ -> index != i })
        }
    }

    fun computePart1(reports: List<List<Int>>): Long {
        return reports.filter { isSafe(it) }.size.toLong()
    }

    fun computePart2(reports: List<List<Int>>): Long {
        return reports.filter { isSafeAfterRemovingElement(it) }.size.toLong()
    }

    override fun computePart1(input: String): Long {
        return computePart1(parse(input))
    }

    override fun computePart2(input: String): Long {
        return computePart2(parse(input))
    }

}
