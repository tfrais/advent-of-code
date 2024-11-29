package cz.frais.aoc.year2023.day01

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day01Test {

    @Test
    fun testCalibrationValueForOneLine() {
        assertThat(calibrationValue("1abc2")).isEqualTo(12)
        assertThat(calibrationValue("a1b2c3d4e5f")).isEqualTo(15)
        assertThat(calibrationValue("treb7uchet")).isEqualTo(77)
    }

    @Test
    fun testCalibrationValueForInputFile() {
        val content = object {}.javaClass.getResource("/2023/day01_test_input_part1.txt")!!.readText()
        assertThat(calibrationValue(content.lines())).isEqualTo(142)
    }

}
