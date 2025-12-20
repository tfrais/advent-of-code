package cz.frais.aoc.year2015

import cz.frais.aoc.year2015.day15.Year2015Day15.computePart1
import cz.frais.aoc.year2015.day15.Year2015Day15.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2015Day15Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2015/day15_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(62842880L)
    }

    @Test
    fun testComputePart2() {
        val input = object {}.javaClass.getResource("/2015/day15_test_input.txt")!!.readText()
        assertThat(computePart2(input)).isEqualTo(57600000L)
    }

}
