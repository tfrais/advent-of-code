package cz.frais.aoc.year2015

import cz.frais.aoc.year2015.day17.Year2015Day17.computePart1
import cz.frais.aoc.year2015.day17.Year2015Day17.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2015Day17Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2015/day17_test_input.txt")!!.readText()
        assertThat(computePart1(input, 25)).isEqualTo(4)
    }

    @Test
    fun testComputePart2() {
        val input = object {}.javaClass.getResource("/2015/day17_test_input.txt")!!.readText()
        assertThat(computePart2(input, 25)).isEqualTo(3)
    }

}
