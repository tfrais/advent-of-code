package cz.frais.aoc.year2024.day17

import cz.frais.aoc.year2024.day17.Year2024Day17.computePart1
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2024Day17Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2024/day17_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo("4,6,3,5,6,3,5,2,1,0")
    }

}
