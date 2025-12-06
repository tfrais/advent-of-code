package cz.frais.aoc.year2025.day04

import cz.frais.aoc.year2025.day04.Year2025Day04.computePart1
import cz.frais.aoc.year2025.day04.Year2025Day04.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2025Day04Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2025/day04_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(13)
    }

    @Test
    fun testComputePart2() {
        val input = object {}.javaClass.getResource("/2025/day04_test_input.txt")!!.readText()
        assertThat(computePart2(input)).isEqualTo(43)
    }

}
