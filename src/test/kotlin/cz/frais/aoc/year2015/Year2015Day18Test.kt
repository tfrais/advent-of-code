package cz.frais.aoc.year2015

import cz.frais.aoc.year2015.day18.Year2015Day18.computePart1
import cz.frais.aoc.year2015.day18.Year2015Day18.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2015Day18Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2015/day18_test_input.txt")!!.readText()
        assertThat(computePart1(input, 4)).isEqualTo(4)
    }

    @Test
    fun testComputePart2() {
        val input = object {}.javaClass.getResource("/2015/day18_test_input.txt")!!.readText()
        assertThat(computePart2(input, 5)).isEqualTo(17)
    }

}
