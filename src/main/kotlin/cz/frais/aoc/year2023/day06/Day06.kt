package cz.frais.aoc.year2023.day06

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

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

@Suppress("MagicNumber")
fun main() {
    val racesPart1 = listOf(
        Race(40L, 215L),
        Race(70L, 1051L),
        Race(98L, 2147L),
        Race(79L, 1005L)
    )
    logger.info { "Part 1 result is ${numberOfWays(racesPart1)}." }

    val racesPart2 = listOf(
        Race(40709879L, 215105121471005L)
    )
    logger.info { "Part 2 result is ${numberOfWays(racesPart2)}." }
}
