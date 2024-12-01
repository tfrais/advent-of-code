package cz.frais.aoc.year2024.day01

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day01Test {

    @Test
    fun testCalculatePart1() {
        val input = object {}.javaClass.getResource("/2024/day01_test_input.txt")!!.readText()
        assertThat(calculatePart1( parse(input.lines()))).isEqualTo(11)
    }

    @Test
    fun testCalculatePart2() {
        val input = object {}.javaClass.getResource("/2024/day01_test_input.txt")!!.readText()
        assertThat(calculatePart2( parse(input.lines()))).isEqualTo(31)
    }

}
