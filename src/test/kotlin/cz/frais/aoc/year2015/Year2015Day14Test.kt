package cz.frais.aoc.year2015

import cz.frais.aoc.year2015.day14.Year2015Day14.computePart1
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2015Day14Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2015/day14_test_input.txt")!!.readText()
        assertThat(computePart1(input, 1000)).isEqualTo(1120)
    }

}
