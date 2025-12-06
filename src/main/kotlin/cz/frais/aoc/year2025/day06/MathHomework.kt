package cz.frais.aoc.year2025.day06

data class MathHomework(
    val operands: List<List<Long>>,
    val operations: List<(Long, Long) -> Long>,
    val initialAccumulatorValue: List<Long>,
) {

    companion object {

        fun fromString(input: String): MathHomework {
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

    }

}