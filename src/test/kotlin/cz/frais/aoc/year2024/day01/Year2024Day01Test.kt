package cz.frais.aoc.year2024.day01

import cz.frais.aoc.year2024.day01.Year2024Day01.computePart1
import cz.frais.aoc.year2024.day01.Year2024Day01.computePart2
import cz.frais.aoc.year2024.day01.Year2024Day01.parse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2024Day01Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2024/day01_test_input.txt")!!.readText()
        assertThat(computePart1(parse(input.lines()))).isEqualTo(11)
    }

    @Test
    fun testComputePart2() {
        val input = object {}.javaClass.getResource("/2024/day01_test_input.txt")!!.readText()
        assertThat(computePart2(parse(input.lines()))).isEqualTo(31)
    }

}
