package cz.frais.aoc.year2024.day11

import cz.frais.aoc.year2024.day11.Year2024Day11.compute
import cz.frais.aoc.year2024.day11.Year2024Day11.numberOfPaths
import cz.frais.aoc.year2024.day11.Year2024Day11.prepareGraph
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigInteger

class Year2024Day11Test {

    @Test
    fun testNumberOfPaths() {
        val graph = prepareGraph(listOf(BigInteger.valueOf(125), BigInteger.valueOf(17)))

        assertThat(graph.numberOfPaths(BigInteger.valueOf(125), 1)).isEqualTo(1)
        assertThat(graph.numberOfPaths(BigInteger.valueOf(125), 2)).isEqualTo(2)
        assertThat(graph.numberOfPaths(BigInteger.valueOf(125), 3)).isEqualTo(2)
        assertThat(graph.numberOfPaths(BigInteger.valueOf(125), 4)).isEqualTo(3)
        assertThat(graph.numberOfPaths(BigInteger.valueOf(125), 5)).isEqualTo(5)

        assertThat(graph.numberOfPaths(BigInteger.valueOf(17), 1)).isEqualTo(2)
        assertThat(graph.numberOfPaths(BigInteger.valueOf(17), 2)).isEqualTo(2)
        assertThat(graph.numberOfPaths(BigInteger.valueOf(17), 3)).isEqualTo(3)
        assertThat(graph.numberOfPaths(BigInteger.valueOf(17), 4)).isEqualTo(6)

        assertThat(graph.numberOfPaths(BigInteger.valueOf(0), 1)).isEqualTo(1)
        assertThat(graph.numberOfPaths(BigInteger.valueOf(1), 1)).isEqualTo(1)
    }

    @Test
    fun testCompute() {
        assertThat(
            compute(
                listOf(BigInteger.valueOf(125), BigInteger.valueOf(17)),
                6
            )
        ).isEqualTo(22)
    }

}
