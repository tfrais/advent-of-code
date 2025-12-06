package cz.frais.aoc.year2025.day06

import cz.frais.aoc.year2025.day06.Year2025Day06.computePart1
import cz.frais.aoc.year2025.day06.Year2025Day06.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2025Day06Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2025/day06_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(4277556)
    }

    @Test
    fun testComputePart2() {
        val input = object {}.javaClass.getResource("/2025/day06_test_input.txt")!!.readText()
        assertThat(computePart2(input)).isEqualTo(3263827)
    }

}
