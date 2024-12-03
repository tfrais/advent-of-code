package cz.frais.aoc.year2023.day05

import cz.frais.aoc.year2023.day05.Year2023Day05.calculate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2023Day05Test {

    private val parser = Parser()

    @Test
    fun testParseInitialElementsCategory() {
        val parserResult = parser.parse(" seeds :  1 23  456 ", false)
        assertThat(parserResult.initialElementCategory).isEqualTo("seed")
    }

    @Test
    fun testParseElementsNotAsRanges() {
        val parserResult = parser.parse(" seeds :  1 23  456 ", false)
        assertThat(parserResult.initialElementRanges).usingRecursiveComparison().isEqualTo(
            listOf(1L..1L, 23L..23L, 456L..456L)
        )
    }

    @Test
    fun testParseElementsAsRanges() {
        val parserResult = parser.parse(" seeds :  79  14 55  13  ", true)
        assertThat(parserResult.initialElementRanges).usingRecursiveComparison().isEqualTo(
            listOf(79L..92L, 55L..67L)
        )
    }

    @Test
    fun testParseAlmanacCategoryMap() {
        val input = " seeds :  1 23  456 \n\nseed-to-soil map:\n50 98 2\n10 15 1"
        val parserResult = parser.parse(input, false)
        assertThat(parserResult.almanac.categoryMap).usingRecursiveComparison().isEqualTo(
            mapOf(
                "seed" to
                        listOf(
                            Almanac.AlmanacMapEntry(50, 98, 2),
                            Almanac.AlmanacMapEntry(10, 15, 1)
                        )
            )
        )
    }

    @Test
    fun testParseAlmanacCategoryOrder() {
        val input = " seeds :  1 23  456 \n\nseed-to-soil map:\n50 98 2\n" +
                "soil-to-fertilizer map:\n0 15 37"
        val parserResult = parser.parse(input, false)
        assertThat(parserResult.almanac.categoryOrder).usingRecursiveComparison().isEqualTo(
            listOf("seed", "soil", "fertilizer")
        )
    }

    @Test
    fun testParseAlmanacForInputFile() {
        val input = object {}.javaClass.getResource("/2023/day05_test_input.txt")!!.readText()
        val parserResult = parser.parse(input, false)
        assertThat(parserResult.initialElementCategory).isEqualTo("seed")
        assertThat(parserResult.initialElementRanges.size).isEqualTo(4)
        assertThat(parserResult.almanac.categoryMap["water"]?.size).isEqualTo(2)
        assertThat(parserResult.almanac.categoryOrder)
            .containsExactly("seed", "soil", "fertilizer", "water", "light", "temperature", "humidity", "location")
    }

    @Test
    fun testAlmanacGetElementWhenMappingExists() {
        val input = " seeds :  1 23  456 \n\nseed-to-soil map:\n50 98 2"
        val parserResult = parser.parse(input, false)
        assertThat(parserResult.almanac.getElement( 99, "seed"))
            .isEqualTo(51)
    }

    @Test
    fun testAlmanacGetElementWhenMappingDoesNotExist() {
        val input = " seeds :  1 23  456 \n\nseed-to-soil map:\n50 98 2"
        val parserResult = parser.parse(input, false)
        assertThat(parserResult.almanac.getElement( 10, "seed"))
            .isEqualTo(10)
    }

    @Test
    fun testCalculatePart1ForInputFile() {
        val input = object {}.javaClass.getResource("/2023/day05_test_input.txt")!!.readText()
        val parserResult = parser.parse(input, false)
        val actual = calculate(parserResult.almanac, parserResult.initialElementRanges)
        assertThat(actual).isEqualTo(35)
    }

    @Test
    fun testCalculatePart2ForInputFile() {
        val input = object {}.javaClass.getResource("/2023/day05_test_input.txt")!!.readText()
        val parserResult = parser.parse(input, true)
        val actual = calculate(parserResult.almanac, parserResult.initialElementRanges)
        assertThat(actual).isEqualTo(46)
    }

}
