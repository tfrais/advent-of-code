package cz.frais.aoc.year2023.day01

import cz.frais.aoc.year2023.day01.Year2023Day01.calibrationValue
import cz.frais.aoc.year2023.day01.Year2023Day01.computePart1
import cz.frais.aoc.year2023.day01.Year2023Day01.computePart2
import cz.frais.aoc.year2023.day01.Year2023Day01.translateDigitNames
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2023Day01Test {

    @Test
    fun testCalibrationValueForOneLine() {
        assertThat(calibrationValue("1abc2")).isEqualTo(12)
        assertThat(calibrationValue("a1b2c3d4e5f")).isEqualTo(15)
        assertThat(calibrationValue("treb7uchet")).isEqualTo(77)
    }

    @Test
    fun testCalibrationValueForInputFilePart1() {
        val content = object {}.javaClass.getResource("/2023/day01_test_input_part1.txt")!!.readText()
        assertThat(computePart1(content)).isEqualTo(142)
    }

    @Test
    fun testTranslateDigitNames() {
        assertThat(translateDigitNames("two1nine")).isEqualTo("219")
        assertThat(translateDigitNames("eightwothree")).isEqualTo("8wo3")
        assertThat(translateDigitNames("4nineeightseven2")).isEqualTo("49872")
        assertThat(translateDigitNames("zoneight234")).isEqualTo("z1ight234")

    }

    @Test
    fun testCalibrationValueForInputFilePart2() {
        val content = object {}.javaClass.getResource("/2023/day01_test_input_part2.txt")!!.readText()
        assertThat(computePart2(content)).isEqualTo(281)
    }

}
