package cz.frais.aoc.year2025.day06

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2025Day06 : AdventOfCodeDaySolution {

    fun compute(mathHomework: MathHomework): Long {
        var result = 0L
        for (i in mathHomework.operands[0].indices) {
            val operands = mathHomework.operands.map { it[i] }
            result += operands.fold(mathHomework.initialAccumulatorValues[i], mathHomework.operations[i])
        }
        return result
    }

    override fun computePart1(input: String): Long =
        compute(MathHomework.fromStringPart1(input))

    override fun computePart2(input: String): Long =
        compute(MathHomework.fromStringPart2(input))

}