package cz.frais.aoc.year2023.day06

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2023Day06 : AdventOfCodeDaySolution {

    fun numberOfWays(race: Race): Long {
        var result = 0L
        for (speed in 0..race.time) {
            val distanceAtSpeed = speed * (race.time - speed)
            if (distanceAtSpeed > race.distance) {
                result++
            }
        }
        return result
    }

    fun numberOfWays(races: List<Race>): Long {
        return races.map { numberOfWays(it) }.reduce { acc, number -> acc * number }
    }

    override fun computePart1(input: String): Long {
        return numberOfWays(
            input.lines().map { it.split(" ") }
                .map { Race(it.first().toLong(), it.last().toLong()) }
                .toList()
                .map { (it) }
        )
    }

    override fun computePart2(input: String): Long = computePart1(input)

}