package cz.frais.aoc.year2023.day05

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day05Test {

    @Test
    fun testParseElements() {
        val almanac = Almanac(" seeds :  1 23  456 ")
        assertThat(almanac.elementCategory).isEqualTo("seed")
        assertThat(almanac.elements).usingRecursiveComparison().isEqualTo(setOf(1L, 23L, 456L))
    }

    @Test
    fun testParseSimpleAlmanac() {
        val almanac = Almanac(" seeds :  1 23  456 \n\nseed-to-soil map:\n50 98 2")
        assertThat(almanac.map).usingRecursiveComparison().isEqualTo(
            setOf(
                Almanac.AlmanacMapEntry(
                    "seed", "soil", 50, 98, 2
                )
            )
        )
    }

    @Test
    fun testParseAlmanacForInputFile() {
        val almanac = Almanac(object {}.javaClass.getResource("/2023/day05_test_input.txt")!!.readText())
        assertThat(almanac.elementCategory).isEqualTo("seed")
        assertThat(almanac.elements.size).isEqualTo(4)
        assertThat(almanac.map.size).isEqualTo(18)
        assertThat(almanac.map.map { it.sourceElementCategory}.distinct())
            .containsExactly("seed", "soil", "fertilizer", "water", "light", "temperature", "humidity")
        assertThat(almanac.map.filter { it.destinationElementCategory == "water"}.size).isEqualTo(4)

    }

}
