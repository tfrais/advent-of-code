package cz.frais.aoc.year2015.day14

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day14 : AdventOfCodeDaySolution {

    fun computePart1(input: String, secondsLimit: Int): Long {
        val reindeers = input.lines().map { Reindeer.fromString(it) }
        var seconds = 0L

        while (seconds < secondsLimit) {
            for (reindeer in reindeers) {
                if (reindeer.flyingSeconds < reindeer.flyingInterval) {
                    reindeer.distanceTravelled += reindeer.speed
                    reindeer.flyingSeconds++
                } else if (reindeer.restingSeconds < reindeer.restingInterval) {
                    reindeer.restingSeconds++
                    if (reindeer.restingSeconds == reindeer.restingInterval) {
                        reindeer.flyingSeconds = 0
                        reindeer.restingSeconds = 0
                    }
                }
            }
            seconds++
        }

        return reindeers.maxOf { it.distanceTravelled }.toLong()
    }

    override fun computePart1(input: String) = computePart1(input, 2503)

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}
