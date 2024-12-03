package cz.frais.aoc.year2023.day08

import io.github.oshai.kotlinlogging.KotlinLogging
import java.math.BigInteger

private val logger = KotlinLogging.logger {}
private val parser = Parser()

fun walk(document: Document): Int {
    var steps = 0
    var currentPosition = "AAA"
    while (currentPosition != "ZZZ") {
        for (instruction in document.instructions) {
            currentPosition = choosePath(instruction, document.nodeMap[currentPosition]!!)
            steps++
        }
    }
    return steps
}

private fun choosePath(instruction: Char, nodes: Pair<String, String>): String {
    return when (instruction) {
        'L' -> nodes.first
        'R' -> nodes.second
        else -> throw IllegalArgumentException("Unknown instruction: $instruction")
    }
}

fun walkGhost(document: Document): BigInteger {
    val currentPositions = document.nodeMap.keys.filter { it.endsWith("A") }.toMutableList()
    val steps = currentPositions.map { _ -> 0 }.toMutableList()
    while (currentPositions.any { !it.endsWith("Z") }) {
        for (instruction in document.instructions) {
            for ((index, currentPosition) in currentPositions.withIndex()) {
                if (!currentPosition.endsWith("Z")) {
                    currentPositions[index] = choosePath(instruction, document.nodeMap[currentPosition]!!)
                    steps[index]++
                }
            }
        }
    }
    return lcm(steps)
}

fun lcm(numbers: List<Int>): BigInteger {
    return numbers.map { it.toBigInteger() }
        .reduce { acc, num -> (acc.multiply(num).divide(acc.gcd(num))) }
}

fun main() {
    val content = object {}.javaClass.getResource("/2023/day08_input.txt")!!.readText()
    val document = parser.parse(content)

    val resultPart1 = walk(document)
    logger.info { "Part 1 result is $resultPart1." }

    val resultPart2 = walkGhost(document)
    logger.info { "Part 2 result is $resultPart2." }
}
