package cz.frais.aoc.year2015

import cz.frais.aoc.year2015.day19.Year2015Day19.computePart1
import cz.frais.aoc.year2015.day19.Year2015Day19.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2015Day19Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2015/day19_test_input_part1.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(4)
    }

    @Test
    fun testComputePart2() {
        val input = object {}.javaClass.getResource("/2015/day19_test_input_part2.txt")!!.readText()
        assertThat(computePart2(input)).isEqualTo(6)
    }

}
