package cz.frais.aoc.year2015

import cz.frais.aoc.year2015.day09.Year2015Day09.computePart1
import cz.frais.aoc.year2015.day09.Year2015Day09.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2015Day09Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2015/day09_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(605)
    }

    @Test
    fun testComputePart2() {
        val input = object {}.javaClass.getResource("/2015/day09_test_input.txt")!!.readText()
        assertThat(computePart2(input)).isEqualTo(982)
    }

}
