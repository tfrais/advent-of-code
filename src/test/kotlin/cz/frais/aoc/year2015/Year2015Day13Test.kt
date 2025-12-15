package cz.frais.aoc.year2015

import cz.frais.aoc.year2015.day13.Year2015Day13.computePart1
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2015Day13Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2015/day13_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(330)
    }

}
