package cz.frais.aoc.year2024.day10

import cz.frais.aoc.year2024.day10.Year2024Day10.computePart1
import cz.frais.aoc.year2024.day10.Year2024Day10.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2024Day10Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2024/day10_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(36)
    }

    @Test
    fun testComputePart2() {
        val input = object {}.javaClass.getResource("/2024/day10_test_input.txt")!!.readText()
        assertThat(computePart2(input)).isEqualTo(81)
    }

}
