package cz.frais.aoc.year2024.day08

import cz.frais.aoc.structures.Position
import cz.frais.aoc.year2024.day08.Year2024Day08.antinodePositionsForPart1
import cz.frais.aoc.year2024.day08.Year2024Day08.buildPairs
import cz.frais.aoc.year2024.day08.Year2024Day08.computePart1
import cz.frais.aoc.year2024.day08.Year2024Day08.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2024Day08Test {

    @Test
    fun testAntinodePositionsForPart1() {
        assertThat(antinodePositionsForPart1(Position(4, 3), Position(5, 5)))
            .containsExactlyInAnyOrder(Position(3, 1), Position(6, 7))

        assertThat(antinodePositionsForPart1(Position(5, 5), Position(4, 3)))
            .containsExactlyInAnyOrder(Position(3, 1), Position(6, 7))

        assertThat(antinodePositionsForPart1(Position(1, 0), Position(2, 0)))
            .containsExactlyInAnyOrder(Position(0, 0), Position(3, 0))

        assertThat(antinodePositionsForPart1(Position(0, 1), Position(0, 2)))
            .containsExactlyInAnyOrder(Position(0, 0), Position(0, 3))
    }

    @Test
    fun testBuildPairs() {
        assertThat(buildPairs(setOf(1, 2, 3)))
            .containsExactlyInAnyOrder(Pair(1, 2), Pair(1, 3), Pair(2, 3))

        assertThat(buildPairs(setOf(1, 2, 3, 4, 5, 6))).hasSize(15)
    }

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2024/day08_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(14)
    }

    @Test
    fun testComputePart2() {
        val input = object {}.javaClass.getResource("/2024/day08_test_input.txt")!!.readText()
        assertThat(computePart2(input)).isEqualTo(34)
    }

}
