package cz.frais.aoc.year2023.day04

import cz.frais.aoc.year2023.day04.Year2023Day04.computePart1
import cz.frais.aoc.year2023.day04.Year2023Day04.computePart2
import cz.frais.aoc.year2023.day04.Year2023Day04.parseCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2023Day04Test {

    @Test
    fun testParseCard() {
        val rawCard = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53 "
        val expected = Card(
            1,
            setOf(41, 48, 83, 86, 17),
            setOf(83, 86, 6, 31, 17, 9, 48, 53)
        )
        assertThat(parseCard(rawCard)).usingRecursiveComparison().isEqualTo(expected)
    }

    @Test
    fun testComputePart1() {
        assertThat(
            computePart1(parseCard("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53 "))
        ).isEqualTo(8)

        assertThat(
            computePart1(parseCard("Card 1: 41 48 83 86 17 | 1 "))
        ).isEqualTo(0)

        assertThat(
            computePart1(parseCard("Card 1: 41 48 83 86 17 | 41 42"))
        ).isEqualTo(1)
    }

    @Test
    fun testComputePart1ForInputFile() {
        val content = object {}.javaClass.getResource("/2023/day04_test_input.txt")!!.readText()
        val actual = computePart1(content.lines().map { parseCard(it) })
        assertThat(actual).isEqualTo(13)
    }

    @Test
    fun testComputePart1Part2ForInputFile() {
        val content = object {}.javaClass.getResource("/2023/day04_test_input.txt")!!.readText()
        val actual = computePart2(content.lines().map { parseCard(it) })
        assertThat(actual).isEqualTo(30)
    }

}
