package cz.frais.aoc.year2025.day05

import cz.frais.aoc.year2025.day05.Year2025Day05.computePart1
import cz.frais.aoc.year2025.day05.Year2025Day05.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2025Day05Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2025/day05_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(3)
    }

    @Test
    fun testComputePart2() {
        val input = object {}.javaClass.getResource("/2025/day05_test_input.txt")!!.readText()
        assertThat(computePart2(input)).isEqualTo(14)
    }

}
