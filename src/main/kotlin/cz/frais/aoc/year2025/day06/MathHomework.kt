package cz.frais.aoc.year2025.day06

import cz.frais.aoc.structures.Table

data class MathHomework(
    val operands: List<List<Long>>,
    val operations: List<(Long, Long) -> Long>,
    val initialAccumulatorValues: List<Long>,
) {

    companion object {

        fun fromStringPart1(input: String): MathHomework {
            val operands = input.lines().dropLast(1).map { line ->
                line.split(Regex("\\s+")).filter { it.isNotEmpty() }
                    .map { it.toLong() }
            }

            val operations = input.lines().last().filter { !it.isWhitespace() }
                .map { symbol ->
                    when (symbol) {
                        '+' -> { l1: Long, l2: Long -> l1 + l2 }
                        '*' -> { l1: Long, l2: Long -> l1 * l2 }
                        else -> throw IllegalArgumentException("Unsupported operation symbol: $symbol")
                    }
                }.toList()

            val initialAccumulatorValues = input.lines().last().filter { !it.isWhitespace() }
                .map { symbol ->
                    when (symbol) {
                        '+' -> 0L
                        '*' -> 1L
                        else -> throw IllegalArgumentException("Unsupported operation symbol: $symbol")
                    }
                }.toList()

            return MathHomework(operands, operations, initialAccumulatorValues)
        }

        fun fromStringPart2(input: String): MathHomework {
            val operations = input.lines().last().filter { !it.isWhitespace() }.reversed()
                .map { symbol ->
                    when (symbol) {
                        '+' -> { l1: Long, l2: Long -> l1 + l2 }
                        '*' -> { l1: Long, l2: Long -> l1 * l2 }
                        else -> throw IllegalArgumentException("Unsupported operation symbol: $symbol")
                    }
                }.toList()

            val initialAccumulatorValues = input.lines().last().filter { !it.isWhitespace() }.reversed()
                .map { symbol ->
                    when (symbol) {
                        '+' -> 0L
                        '*' -> 1L
                        else -> throw IllegalArgumentException("Unsupported operation symbol: $symbol")
                    }
                }.toList()

            val operandsTable = Table(input.lines().dropLast(1).joinToString(separator = "\n")) { it }
            var operands = mutableListOf<List<Long>>()
            var currentProblem = mutableListOf<Long>()
            for (c in operandsTable.width() - 1 downTo 0) {
                val columnString = operandsTable.getColumn(c)

                // "Problems are still separated with a column consisting only of spaces"
                if (columnString.all { it.isWhitespace() }) {
                    operands.add(currentProblem)
                    currentProblem = mutableListOf<Long>()
                } else {
                    currentProblem.add(
                        columnString.filter { !it.isWhitespace() }.joinToString(separator = "").toLong()
                    )
                }
            }
            operands.add(currentProblem)

            return MathHomework(operands, operations, initialAccumulatorValues)
        }

    }

}