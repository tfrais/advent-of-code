package cz.frais.aoc.year2025.day07

import cz.frais.aoc.year2025.day07.Year2025Day07.computePart1
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2025Day07Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2025/day07_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(21)
    }

}
