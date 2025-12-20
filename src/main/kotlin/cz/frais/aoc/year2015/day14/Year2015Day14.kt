package cz.frais.aoc.year2015.day14

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day14 : AdventOfCodeDaySolution {

    fun computePart1(input: String, secondsLimit: Int): Long {
        val reindeers = input.lines().map { Reindeer.fromString(it) }
        var seconds = 0L

        while (seconds < secondsLimit) {
            for (reindeer in reindeers) {
                reindeer.fly()
            }
            seconds++
        }

        return reindeers.maxOf { it.distanceTravelled }.toLong()
    }

    fun computePart2(input: String, secondsLimit: Int): Long {
        val reindeers = input.lines().map { Reindeer.fromString(it) }
        var seconds = 0L

        while (seconds < secondsLimit) {
            for (reindeer in reindeers) {
                reindeer.fly()
            }
            val maxDistance = reindeers.maxOf { it.distanceTravelled }
            reindeers.filter { it.distanceTravelled == maxDistance }.forEach { it.score++ }
            seconds++
        }

        return reindeers.maxOf { it.score }.toLong()
    }

    override fun computePart1(input: String) = computePart1(input, 2503)
    override fun computePart2(input: String) = computePart2(input, 2503)

}
