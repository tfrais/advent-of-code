package cz.frais.aoc.year2023.day08

import cz.frais.aoc.AdventOfCodeDaySolution
import java.math.BigInteger

object Year2023Day08 : AdventOfCodeDaySolution {

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

    override fun computePart1(input: String): Long {
        return walk(parser.parse(input)).toLong()
    }

    override fun computePart2(input: String): Long {
        return walkGhost(parser.parse(input)).toLong()

    }

}
