package cz.frais.aoc.year2025.day03

import cz.frais.aoc.year2025.day03.Year2025Day03.computePart1
import cz.frais.aoc.year2025.day03.Year2025Day03.computePart2
import cz.frais.aoc.year2025.day03.Year2025Day03.maximumJoltagePart1
import cz.frais.aoc.year2025.day03.Year2025Day03.maximumJoltagePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2025Day03Test {

    @Test
    fun testMaximumJoltagePart1() {
        assertThat(maximumJoltagePart1("987654321111111")).isEqualTo(98)
        assertThat(maximumJoltagePart1("811111111111119")).isEqualTo(89)
        assertThat(maximumJoltagePart1("234234234234278")).isEqualTo(78)
        assertThat(maximumJoltagePart1("818181911112111")).isEqualTo(92)
    }

    @Test
    fun testMaximumJoltagePart2() {
        assertThat(maximumJoltagePart2("987654321111111")).isEqualTo(987654321111)
        assertThat(maximumJoltagePart2("811111111111119")).isEqualTo(811111111119)
        assertThat(maximumJoltagePart2("234234234234278")).isEqualTo(434234234278)
        assertThat(maximumJoltagePart2("818181911112111")).isEqualTo(888911112111)
    }

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2025/day03_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(357)
    }

    @Test
    fun testComputePart2() {
        val input = object {}.javaClass.getResource("/2025/day03_test_input.txt")!!.readText()
        assertThat(computePart2(input)).isEqualTo(3121910778619)
    }

}
