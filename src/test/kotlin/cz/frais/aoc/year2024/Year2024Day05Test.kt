package cz.frais.aoc.year2024


import cz.frais.aoc.year2024.day05.Parser
import cz.frais.aoc.year2024.day05.Year2024Day05.computePart1
import cz.frais.aoc.year2024.day05.Year2024Day05.isSortedCorrectly
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2024Day05Test {

    @Test
    fun testIsSortedCorrectly() {
        val input = object {}.javaClass.getResource("/2024/day05_test_input.txt")!!.readText()
        val pageOrderRules = Parser.parse(input).pageOrderRules
        assertThat(isSortedCorrectly(pageOrderRules, listOf(75, 47, 61, 53, 29))).isTrue()
        assertThat(isSortedCorrectly(pageOrderRules, listOf(75, 97, 47, 61, 53))).isFalse()
    }

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2024/day05_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(143)
    }

}
