package cz.frais.aoc.year2024.day04


import cz.frais.aoc.year2024.day04.Year2024Day04.computePart1
import cz.frais.aoc.year2024.day04.Year2024Day04.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2024Day04Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2024/day04_test_input.txt")!!.readText()
        assertThat(computePart1(input, "XMAS")).isEqualTo(18)
    }

    @Test
    fun testComputePart2() {
        val input = object {}.javaClass.getResource("/2024/day04_test_input.txt")!!.readText()
        assertThat(computePart2(input, "MAS")).isEqualTo(9)
    }

}
