package cz.frais.aoc.year2024.day19

import cz.frais.aoc.year2024.day19.Year2024Day19.compute
import cz.frais.aoc.year2024.day19.Year2024Day19.computePart1
import cz.frais.aoc.year2024.day19.Year2024Day19.computePart2
import cz.frais.aoc.year2024.day19.Year2024Day19.isSolvable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2024Day19Test {

    @Test
    fun testIsSolvable() {
        val towelPatterns = listOf("r", "wr", "b", "g", "bwu", "rb", "gb", "br")
        assertThat(isSolvable(towelPatterns, "brwrr")).isTrue()
        assertThat(isSolvable(towelPatterns, "bggr")).isTrue()
        assertThat(isSolvable(towelPatterns, "gbbr")).isTrue()
        assertThat(isSolvable(towelPatterns, "rrbgbr")).isTrue()
        assertThat(isSolvable(towelPatterns, "ubwu")).isFalse()
        assertThat(isSolvable(towelPatterns, "bwurrg")).isTrue()
        assertThat(isSolvable(towelPatterns, "brgr")).isTrue()
        assertThat(isSolvable(towelPatterns, "bbrgwb")).isFalse()
    }

    @Test
    fun testCompute() {
        val towelPatterns = listOf("r", "wr", "b", "g", "bwu", "rb", "gb", "br")

        assertThat(compute(towelPatterns, "ubwu")).isEmpty()

        assertThat(compute(towelPatterns, "bwurrg")).containsExactly(
            listOf("bwu", "r", "r", "g")
        )

        assertThat(compute(towelPatterns, "bwurrg")).containsExactly(
            listOf("bwu", "r", "r", "g")
        )

    }

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2024/day19_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(6)
    }

    @Test
    fun testComputePart2() {
        val input = object {}.javaClass.getResource("/2024/day19_test_input.txt")!!.readText()
        assertThat(computePart2(input)).isEqualTo(16)
    }

}
