package cz.frais.aoc.year2015.day24

import cz.frais.aoc.AdventOfCodeDaySolution
import kotlin.collections.fold

object Year2015Day24 : AdventOfCodeDaySolution {

    fun <T> List<T>.combinations(n: Int): List<List<T>> {
        require(n in 0..size)

        fun recurse(start: Int, current: MutableList<T>): List<List<T>> {
            if (current.size == n) return listOf(current.toList())

            val result = mutableListOf<List<T>>()
            for (i in start until size) {
                current.add(this[i])
                result += recurse(i + 1, current)
                current.removeAt(current.lastIndex)
            }
            return result
        }

        return recurse(0, mutableListOf())
    }

    fun compute(input: String, groupsNum: Int): Long {
        val weights = input.lines().map { it.toInt() }
        require(weights.sum() % groupsNum == 0)
        val target = weights.sum() / groupsNum

        for (n in 1..weights.size) {
            var found = false
            var min = Long.MAX_VALUE
            for (group in weights.combinations(n)) {
                if (group.sum() == target) {
                    found = true
                    val current = group.fold(1L) { acc, x -> acc * x }
                    if (current < min) {
                        min = current
                    }
                }
            }
            if (found) {
                return min
            }
        }

        error("No solution found")
    }

    override fun computePart1(input: String) = compute(input, 3)

    override fun computePart2(input: String) = compute(input, 4)

}
