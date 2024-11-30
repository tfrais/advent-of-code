package cz.frais.aoc.year2023.day08

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
private val parser = Parser()

internal fun walk(document: Document): Int {
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

fun main() {
    val content = object {}.javaClass.getResource("/2023/day08_input.txt")!!.readText()
    val resultPart1 = walk(parser.parse(content))
    logger.info { "Part 1 result is $resultPart1." }
}
