package cz.frais.aoc.year2025.day06

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2025Day06 : AdventOfCodeDaySolution {

    override fun computePart1(input: String): Long {
        val mathHomework = MathHomework.fromString(input)
        var result = 0L
        for (i in mathHomework.operands[0].indices) {
            val operands = mathHomework.operands.map { it[i] }
            result += operands.fold(mathHomework.initialAccumulatorValue[i], mathHomework.operations[i])
        }
        return result
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}