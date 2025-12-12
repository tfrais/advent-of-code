package cz.frais.aoc.year2025.day11

import cz.frais.aoc.year2025.day11.Year2025Day11.computePart1
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2025Day11Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2025/day11_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(5)
    }

}
