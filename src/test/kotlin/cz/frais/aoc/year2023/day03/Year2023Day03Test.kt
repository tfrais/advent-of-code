package cz.frais.aoc.year2023.day03

import cz.frais.aoc.year2023.day03.Year2023Day03.computeSumOfGearRatios
import cz.frais.aoc.year2023.day03.Year2023Day03.computeSumOfNumbersAdjacentToSymbol
import cz.frais.aoc.year2023.day03.Year2023Day03.detectFullNumber
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class Year2023Day03Test {

    @Test
    fun testPlanCreationSuccessful() {
        val plan = Plan("...\n...")
        assertThat(plan.getWidth()).isEqualTo(3)
        assertThat(plan.getHeight()).isEqualTo(2)
    }

    @Test
    fun testPlanCreationFailed() {
        assertThatThrownBy {
            Plan("...\n..")
        }.isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun testPlanGetInsidePosition() {
        val plan = Plan("ABC\nDEF")
        assertThat(plan.get(0, 0)).isEqualTo('A')
        assertThat(plan.get(0, 1)).isEqualTo('D')
        assertThat(plan.get(2, 1)).isEqualTo('F')

    }

    @Test
    fun testPlanGetOutsidePosition() {
        val plan = Plan("ABC\nDEF")
        assertThat(plan.get(-1, 0)).isEqualTo('.')
        assertThat(plan.get(0, -1)).isEqualTo('.')
        assertThat(plan.get(1, 5)).isEqualTo('.')
        assertThat(plan.get(5, 1)).isEqualTo('.')
    }

    @Test
    fun testDetectFullNumber() {
        val plan = Plan(".12..567\n012.456.")
        assertThat(detectFullNumber(plan, 2, 0)).usingRecursiveComparison().isEqualTo(
            DetectNumberResult(12, 1, 0, 2)
        )
        assertThat(detectFullNumber(plan, 1, 0)).usingRecursiveComparison().isEqualTo(
            DetectNumberResult(12, 1, 0, 2)
        )
        assertThat(detectFullNumber(plan, 7, 0)).usingRecursiveComparison().isEqualTo(
            DetectNumberResult(567, 5, 0, 3)
        )
        assertThat(detectFullNumber(plan, 0, 1)).usingRecursiveComparison().isEqualTo(
            DetectNumberResult(12, 0, 1, 3)
        )
        assertThat(detectFullNumber(plan, 5, 1)).usingRecursiveComparison().isEqualTo(
            DetectNumberResult(456, 4, 1, 3)
        )
    }

    @Test
    fun testSumOfNumbersAdjacentToSymbolForInputFile() {
        val content = object {}.javaClass.getResource("/2023/day03_test_input.txt")!!.readText()
        val actual = computeSumOfNumbersAdjacentToSymbol(Plan(content))
        assertThat(actual).isEqualTo(4361)
    }

    @Test
    fun testComputeSumOfNumbersAdjacentToSymbol() {
        assertThat(computeSumOfNumbersAdjacentToSymbol(Plan("..%\n.1."))).isEqualTo(1)
        assertThat(computeSumOfNumbersAdjacentToSymbol(Plan("..%\n1.."))).isEqualTo(0)
    }

    @Test
    fun testSumOfGearRatioForInputFile() {
        val content = object {}.javaClass.getResource("/2023/day03_test_input.txt")!!.readText()
        val actual = computeSumOfGearRatios(Plan(content))
        assertThat(actual).isEqualTo(467835)
    }

}
