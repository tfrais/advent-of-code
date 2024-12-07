package cz.frais.aoc.year2024.day07

import cz.frais.aoc.year2024.day07.Year2024Day07.ALLOWED_OPERATORS_PART1
import cz.frais.aoc.year2024.day07.Year2024Day07.computePart1
import cz.frais.aoc.year2024.day07.Year2024Day07.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2024Day07Test {

    @Test
    fun testPossibleOperatorCombinations() {
        assertThat(Equation(0, listOf(1, 2), ALLOWED_OPERATORS_PART1).possibleOperatorCombinations())
            .containsExactlyInAnyOrder(
                listOf(Operator.ADD),
                listOf(Operator.MULTIPLY)
            )

        assertThat(Equation(0, listOf(1, 2, 3), ALLOWED_OPERATORS_PART1).possibleOperatorCombinations())
            .containsExactlyInAnyOrder(
                listOf(Operator.ADD, Operator.ADD),
                listOf(Operator.ADD, Operator.MULTIPLY),
                listOf(Operator.MULTIPLY, Operator.ADD),
                listOf(Operator.MULTIPLY, Operator.MULTIPLY),
            )
    }

    @Test
    fun testOperatorsSolvingEquation() {
        assertThat(Equation(83, listOf(17, 5), ALLOWED_OPERATORS_PART1).operatorsSolvingEquation())
            .isEmpty()

        assertThat(Equation(190, listOf(10, 19), ALLOWED_OPERATORS_PART1).operatorsSolvingEquation())
            .containsExactlyInAnyOrder(
                listOf(Operator.MULTIPLY)
            )

        assertThat(Equation(3267, listOf(81, 40, 27), ALLOWED_OPERATORS_PART1).operatorsSolvingEquation())
            .containsExactlyInAnyOrder(
                listOf(Operator.ADD, Operator.MULTIPLY),
                listOf(Operator.MULTIPLY, Operator.ADD)
            )
    }

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2024/day07_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(3749)
    }

    @Test
    fun testComputePart2() {
        val input = object {}.javaClass.getResource("/2024/day07_test_input.txt")!!.readText()
        assertThat(computePart2(input)).isEqualTo(11387)
    }

}
