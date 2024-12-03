package cz.frais.aoc.year2024.day01

import cz.frais.aoc.AdventOfCodeDaySolution
import kotlin.math.abs

object Year2024Day01 : AdventOfCodeDaySolution {

    fun parse(lines: List<String>): Pair<List<Long>, List<Long>> {
        val listLeft = mutableListOf<Long>()
        val listRight = mutableListOf<Long>()
        lines.forEach { line ->
            val split = line.trim().split(Regex("\\s+")).map { it.toLong() }
            listLeft.add(split.first())
            listRight.add(split.last())
        }
        return Pair(listLeft, listRight)
    }

    fun computePart1(lists: Pair<List<Long>, List<Long>>): Long {
        val sortedListLeft = lists.first.sorted()
        val sortedListRight = lists.second.sorted()
        var sumDistance = 0L
        for (i in sortedListLeft.indices) {
            sumDistance += abs(sortedListLeft[i] - sortedListRight[i])
        }
        return sumDistance
    }

    fun computePart2(lists: Pair<List<Long>, List<Long>>): Long {
        val rightFrequencyMap: Map<Long, Int> = lists.second.groupingBy { it }.eachCount()
        var similarityScore = 0L
        lists.first.forEach { similarityScore += it * (rightFrequencyMap[it] ?: 0) }
        return similarityScore
    }

    override fun computePart1(input: String): Long {
        return computePart1(parse(input.lines()))
    }

    override fun computePart2(input: String): Long {
        return computePart2(parse(input.lines()))
    }

}