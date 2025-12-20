package cz.frais.aoc.year2015.day17

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day17 : AdventOfCodeDaySolution {

    fun computePart1(input: String, capacity: Int): Long {
        val containers = input.lines().map { it.toInt() }
        var result = 0L
        val inProgress = mutableSetOf(listOf<Boolean>())
        while (inProgress.isNotEmpty()) {
            val current = inProgress.first()
            inProgress.remove(current)

            if (current.size < containers.size) {
                inProgress.add(current + true)
                inProgress.add(current + false)
            } else {
                if (capacity == containers.zip(current).sumOf { (container, comb) -> if (comb) container else 0 }) {
                    result++
                }
            }
        }

        return result
    }

    fun computePart2(input: String, capacity: Int): Long {
        val containers = input.lines().map { it.toInt() }
        var minimiumContainers = Int.MAX_VALUE
        var numberOfMinimiumContainers = 0L
        val inProgress = mutableSetOf(listOf<Boolean>())
        while (inProgress.isNotEmpty()) {
            val current = inProgress.first()
            inProgress.remove(current)

            if (current.size < containers.size) {
                inProgress.add(current + true)
                inProgress.add(current + false)
            } else {
                if (capacity == containers.zip(current).sumOf { (container, comb) -> if (comb) container else 0 }) {
                    val usedContainers = current.filter { it }.size
                    if (usedContainers < minimiumContainers) {
                        minimiumContainers = current.filter { it }.size
                        numberOfMinimiumContainers = 0L
                    }
                    if (usedContainers == minimiumContainers) {
                        numberOfMinimiumContainers++
                    }
                }
            }
        }

        return numberOfMinimiumContainers
    }

    override fun computePart1(input: String): Long = computePart1(input, 150)

    override fun computePart2(input: String): Long = computePart2(input, 150)

}
