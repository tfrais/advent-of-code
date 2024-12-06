package cz.frais.aoc

import io.github.oshai.kotlinlogging.KotlinLogging

object ConsoleApp {

    private val logger = KotlinLogging.logger {}

    private val solution: AdventOfCodeDaySolution =
        cz.frais.aoc.year2024.day06.Year2024Day06

    private val input: String =
        object {}.javaClass.getResource("/2024/day06_input.txt")!!.readText()

    @JvmStatic
    fun main(args: Array<String>) {
        logger.info { "Part 1 result is ${solution.computePart1(input)}." }
        logger.info { "Part 2 result is ${solution.computePart2(input)}." }
    }

}
