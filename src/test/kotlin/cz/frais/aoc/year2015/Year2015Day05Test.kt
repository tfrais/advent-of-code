package cz.frais.aoc.year2015

import cz.frais.aoc.year2015.day05.Rule
import cz.frais.aoc.year2015.day05.Year2015Day05.RULES_PART1
import cz.frais.aoc.year2015.day05.Year2015Day05.isNice
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2015Day05Test {

    @Test
    fun testIsNicePart1() {
        assertThat(isNice("ugknbfddgicrmopn", RULES_PART1)).isTrue()
        assertThat(isNice("aaa", RULES_PART1)).isTrue()
        assertThat(isNice("jchzalrnumimnmhp", RULES_PART1)).isFalse()
        assertThat(isNice("haegwjzuvuyypxyu", RULES_PART1)).isFalse()
        assertThat(isNice("dvszwmarrgswjxmb", RULES_PART1)).isFalse()
    }

    @Test
    fun testIsNicePart2() {
        assertThat(Rule.TWO_CHARS_TWICE_NO_OVERLAP.isNice("xyxy")).isTrue()
        assertThat(Rule.TWO_CHARS_TWICE_NO_OVERLAP.isNice("aabcdefgaa")).isTrue()
        assertThat(Rule.TWO_CHARS_TWICE_NO_OVERLAP.isNice("aaa")).isFalse()

        assertThat(Rule.TWO_SAME_CHARS_ANOTHER_IN_BETWEEN.isNice("xyx")).isTrue()
        assertThat(Rule.TWO_SAME_CHARS_ANOTHER_IN_BETWEEN.isNice("abcdefeghi")).isTrue()
        assertThat(Rule.TWO_SAME_CHARS_ANOTHER_IN_BETWEEN.isNice("aaa")).isTrue()
        assertThat(Rule.TWO_SAME_CHARS_ANOTHER_IN_BETWEEN.isNice("abc")).isFalse()
    }

}
