package cz.frais.aoc.year2015.day23

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day23 : AdventOfCodeDaySolution {

    fun compute(input: String, initialRegisterA: Long): Long {
        val instructions = input.lines()
        var instructionPointer = 0
        val registerMap = mutableMapOf("a" to initialRegisterA, "b" to 0L)

        while (instructionPointer in instructions.indices) {
            val instructionString = instructions[instructionPointer].take(4).trim()
            val operandString = instructions[instructionPointer].drop(4)
            when (instructionString) {
                "hlf" -> {
                    registerMap[operandString] = registerMap[operandString]!! / 2
                    instructionPointer++
                }

                "tpl" -> {
                    registerMap[operandString] = registerMap[operandString]!! * 3
                    instructionPointer++
                }

                "inc" -> {
                    registerMap[operandString] = registerMap[operandString]!! + 1
                    instructionPointer++
                }

                "jmp" -> {
                    instructionPointer += operandString.toInt()
                }

                "jie" -> {
                    if (registerMap[operandString[0].toString()]!! % 2 == 0L) {
                        instructionPointer += operandString.substringAfter(",").trim().toInt()
                    } else {
                        instructionPointer++
                    }
                }

                "jio" -> {
                    if (registerMap[operandString[0].toString()]!! == 1L) {
                        instructionPointer += operandString.substringAfter(",").trim().toInt()
                    } else {
                        instructionPointer++
                    }
                }

                else -> error("Unknown instruction: $instructionString")
            }

        }

        return registerMap["b"]!!
    }

    override fun computePart1(input: String): Long = compute(input, 0L)

    override fun computePart2(input: String): Long = compute(input, 1L)

}
