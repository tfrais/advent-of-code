package cz.frais.aoc.year2015

import cz.frais.aoc.year2015.day08.Year2015Day08.computePart1
import cz.frais.aoc.year2015.day08.Year2015Day08.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2015Day08Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2015/day08_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(12)
    }

    @Test
    fun testComputePart2() {
        val input = object {}.javaClass.getResource("/2015/day08_test_input.txt")!!.readText()
        assertThat(computePart2(input)).isEqualTo(19)
    }

}
