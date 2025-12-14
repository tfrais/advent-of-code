package cz.frais.aoc.year2025.day09

import cz.frais.aoc.year2025.day09.Year2025Day09.computePart1
import cz.frais.aoc.year2025.day09.Year2025Day09.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2025Day09Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2025/day09_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(50)
    }

    @Test
    fun testComputePart2() {
        val input = object {}.javaClass.getResource("/2025/day09_test_input.txt")!!.readText()
        assertThat(computePart2(input)).isEqualTo(24)
    }

}
