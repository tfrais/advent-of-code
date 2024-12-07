package cz.frais.aoc.year2024.day07

data class Equation(
    val targetResult: Long,
    val operands: List<Long>,
    val allowedOperators: Set<Operator>,
) {

    companion object {

        private const val TARGET_STRING_DELIMITER = ":"
        private const val OPERAND_STRING_DELIMITER = " "

        fun fromString(rawEquation: String, allowedOperators: Set<Operator>): Equation =
            rawEquation.split(TARGET_STRING_DELIMITER).let { parts ->
                Equation(
                    targetResult = parts.first().toLong(),
                    operands = parts.last().trim()
                        .split(OPERAND_STRING_DELIMITER).map { it.toLong() },
                    allowedOperators = allowedOperators
                )
            }

    }

    fun possibleOperatorCombinations(): Set<List<Operator>> =
        if (operands.size == 2) {
            allowedOperators.map { listOf(it) }.toSet()
        } else {
            Equation(
                targetResult = targetResult,
                operands = operands.drop(1),
                allowedOperators = allowedOperators
            ).possibleOperatorCombinations().flatMap { list ->
                allowedOperators.map { operator -> listOf(operator) + list }
            }.toSet()
        }

    fun operatorsSolvingEquation(): Set<List<Operator>> =
        possibleOperatorCombinations()
            .asSequence()
            .filter { operatorsList ->
                operatorsList.foldIndexed(operands.first()) { index, acc, operator ->
                    if (acc > targetResult) return@filter false
                    operator.operation(acc, operands[index + 1])
                } == targetResult
            }
            .toSet()

}
