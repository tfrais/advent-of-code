package cz.frais.aoc.year2015

import cz.frais.aoc.year2015.day11.Year2015Day11.hasStraightOfThreeLetters
import cz.frais.aoc.year2015.day11.Year2015Day11.hasTwoPairsOfSameLetters
import cz.frais.aoc.year2015.day11.Year2015Day11.nextInSequence
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2015Day11Test {

    @Test
    fun testNextInSequence() {
        assertThat(nextInSequence("abcdefga")).isEqualTo("abcdefgb")
        assertThat(nextInSequence("abcdefgz")).isEqualTo("abcdefha")
        assertThat(nextInSequence("abcdefzz")).isEqualTo("abcdegaa")
    }

    @Test
    fun testHasStraightOfThreeLetters() {
        assertThat(hasStraightOfThreeLetters("hijklmmn")).isTrue
        assertThat(hasStraightOfThreeLetters("abbceffg")).isFalse
    }

    @Test
    fun testHasTwoPairsOfSameLetters() {
        assertThat(hasTwoPairsOfSameLetters("abbceffg")).isTrue
        assertThat(hasTwoPairsOfSameLetters("hijklmmn")).isFalse
    }

}
