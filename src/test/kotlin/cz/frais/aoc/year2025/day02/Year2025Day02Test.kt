package cz.frais.aoc.year2025.day02

import cz.frais.aoc.year2025.day02.Year2025Day02.computePart1
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2025Day02Test {

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2025/day02_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(1227775554)
    }

}
