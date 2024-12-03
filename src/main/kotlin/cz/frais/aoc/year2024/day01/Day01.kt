package cz.frais.aoc.year2024.day01

import kotlin.math.abs

fun parse(lines: List<String>): Pair<List<Int>, List<Int>> {
    val listLeft = mutableListOf<Int>()
    val listRight = mutableListOf<Int>()
    lines.forEach { line ->
        val split = line.trim().split(Regex("\\s+")).map { it.toInt() }
        listLeft.add(split.first())
        listRight.add(split.last())
    }
    return Pair(listLeft, listRight)
}

fun calculatePart1(lists: Pair<List<Int>, List<Int>>): Int {
    val sortedListLeft = lists.first.sorted()
    val sortedListRight = lists.second.sorted()
    var sumDistance = 0
    for (i in sortedListLeft.indices) {
        sumDistance += abs(sortedListLeft[i] - sortedListRight[i])
    }
    return sumDistance
}

fun calculatePart2(lists: Pair<List<Int>, List<Int>>): Int {
    val rightFrequencyMap: Map<Int, Int> = lists.second.groupingBy { it }.eachCount()
    var similarityScore = 0
    lists.first.forEach { similarityScore += it * (rightFrequencyMap[it] ?: 0) }
    return similarityScore
}

fun main() {
    val content = object {}.javaClass.getResource("/2024/day01_input.txt")!!.readText()
    val parsed = parse(content.lines())
    println("Part 1 result is ${calculatePart1(parsed)}.")
    println("Part 2 result is ${calculatePart2(parsed)}.")
}
