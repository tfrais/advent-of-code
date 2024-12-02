package cz.frais.aoc.year2024.day02

import kotlin.math.abs
import kotlin.math.sign

private const val DIFFERENCE_TOLERANCE_MIN = 1
private const val DIFFERENCE_TOLERANCE_MAX = 3

internal fun parse(input: String): List<List<Int>> {
    return input.split("\n")
        .map { line -> line.split(" ").map { it.toInt() } }
}

internal fun isSafe(report: List<Int>): Boolean {
    val diffs = report.zipWithNext { a, b -> b - a }
    val absDiffs = diffs.map { abs(it) }
    return absDiffs.min() >= DIFFERENCE_TOLERANCE_MIN && absDiffs.max() <= DIFFERENCE_TOLERANCE_MAX
            && diffs.map { sign(it.toDouble()) }.distinct().size == 1
}

internal fun calculatePart1(reports: List<List<Int>>): Int {
    return reports.filter { isSafe(it) }.size
}

fun main() {
    val content = object {}.javaClass.getResource("/2024/day02_input.txt")!!.readText()
    val parsed = parse(content)
    println("Part 1 result is ${calculatePart1(parsed)}.")
}
