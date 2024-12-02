package cz.frais.aoc.year2023.day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day10Test {

    @Test
    fun testMoveVectorApply() {
        assertThat(MoveVector(1, -1).apply(Position(5, 5)))
            .usingRecursiveComparison().isEqualTo(Position(6, 4))
    }

    @Test
    fun testPlanAdjacentPositions() {
        assertThat(Plan("..").adjacentPositions(Position(0, 0)))
            .usingRecursiveComparison().isEqualTo(listOf(Position(1, 0)))
    }

    @Test
    fun testPlanStartPosition() {
        assertThat(Plan("...\n..S").startPosition())
            .usingRecursiveComparison().isEqualTo(Position(2, 1))
    }

    @Test
    fun testPipeCalculatorNextPositions() {
        assertThat(PipeCalculator.nextPositions(Position(5, 5), Symbol.NE_BEND))
            .usingRecursiveComparison().isEqualTo(
                listOf(
                    Position(6, 5),
                    Position(5, 4)
                )
            )
    }

    @Test
    fun testPipeCalculator() {
        val input = object {}.javaClass.getResource("/2023/day10_test_input_part1_plan1.txt")!!.readText()
        assertThat(PipeCalculator.pipe(Plan(input))).containsExactly(
            Position(1, 1),
            Position(1, 2),
            Position(1, 3),
            Position(2, 3),
            Position(3, 3),
            Position(3, 2),
            Position(3, 1),
            Position(2, 1)
        )
    }

    @Test
    fun testCalculatePart1Plan1() {
        val input = object {}.javaClass.getResource("/2023/day10_test_input_part1_plan1.txt")!!.readText()
        assertThat(calculatePart1(Plan(input))).isEqualTo(4)
    }

    @Test
    fun testCalculatePart1Plan2() {
        val input = object {}.javaClass.getResource("/2023/day10_test_input_part1_plan2.txt")!!.readText()
        assertThat(calculatePart1(Plan(input))).isEqualTo(8)
    }

    @Test
    fun testCalculatePart2Plan1() {
        val input = object {}.javaClass.getResource("/2023/day10_test_input_part2_plan1.txt")!!.readText()
        assertThat(calculatePart2(Plan(input))).isEqualTo(4)
    }

    @Test
    fun testCalculatePart2Plan2() {
        val input = object {}.javaClass.getResource("/2023/day10_test_input_part2_plan2.txt")!!.readText()
        assertThat(calculatePart2(Plan(input))).isEqualTo(4)
    }

    @Test
    fun testCalculatePart2Plan3() {
        val input = object {}.javaClass.getResource("/2023/day10_test_input_part2_plan3.txt")!!.readText()
        assertThat(calculatePart2(Plan(input))).isEqualTo(8)
    }

    @Test
    fun testCalculatePart2Plan4() {
        val input = object {}.javaClass.getResource("/2023/day10_test_input_part2_plan4.txt")!!.readText()
        assertThat(calculatePart2(Plan(input))).isEqualTo(10)
    }

}
