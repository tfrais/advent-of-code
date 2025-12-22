package cz.frais.aoc

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.reflect.KFunction1
import kotlin.system.measureTimeMillis

object ConsoleApp {

    private val logger = KotlinLogging.logger {}

    private val solution: AdventOfCodeDaySolution = cz.frais.aoc.year2015.day21.Year2015Day21
    private val input: String = object {}.javaClass.getResource("/2015/day21_input.txt")!!.readText()

    @JvmStatic
    fun main(args: Array<String>) {
        fun runPart(i: Int, function: KFunction1<String, Long>) {
            var result: Long?
            val duration = measureTimeMillis {
                result = function(input)
            }
            logger.info { "Part $i result: $result, executed in $duration ms" }
        }

        runPart(1, solution::computePart1)
        runPart(2, solution::computePart2)
    }

}
