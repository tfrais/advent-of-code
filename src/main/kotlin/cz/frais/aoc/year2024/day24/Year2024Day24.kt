package cz.frais.aoc.year2024.day24

import cz.frais.aoc.AdventOfCodeDaySolution

@Suppress("MagicNumber")
object Year2024Day24 : AdventOfCodeDaySolution {

    override fun computePart1(input: String): Long {
        val system = Parser.parse(input)
        while (system.remainingGates.isNotEmpty()) {
            system.solveNextGate()
        }
        return system.finalOutput()
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}
