package cz.frais.aoc.year2025.day01

import cz.frais.aoc.year2025.day01.Year2025Day01.computePart1
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2025Day01Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2025/day01_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(3)
    }

}
