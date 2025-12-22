package cz.frais.aoc.year2015

import cz.frais.aoc.year2015.day24.Year2015Day24.computePart1
import cz.frais.aoc.year2015.day24.Year2015Day24.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2015Day24Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2015/day24_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(99)
    }

    @Test
    fun testComputePart2() {
        val input = object {}.javaClass.getResource("/2015/day24_test_input.txt")!!.readText()
        assertThat(computePart2(input)).isEqualTo(44)
    }

}
