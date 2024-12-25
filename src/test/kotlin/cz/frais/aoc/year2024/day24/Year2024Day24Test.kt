package cz.frais.aoc.year2024.day24

import cz.frais.aoc.year2024.day24.Year2024Day24.computePart1
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2024Day24Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2024/day24_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(4)
    }

}
