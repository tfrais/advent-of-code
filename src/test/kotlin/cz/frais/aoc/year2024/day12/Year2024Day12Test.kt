package cz.frais.aoc.year2024.day12

import cz.frais.aoc.structures.Position
import cz.frais.aoc.year2024.day12.Year2024Day12.computePart1
import cz.frais.aoc.year2024.day12.Year2024Day12.computePart2
import cz.frais.aoc.year2024.day12.Year2024Day12.perimeterOfRegion
import cz.frais.aoc.year2024.day12.Year2024Day12.sidesOfRegion
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2024Day12Test {

    @Test
    fun testPerimeterOfRegion() {
        assertThat(
            perimeterOfRegion(
                setOf(
                    Position(0, 0),
                    Position(1, 0),
                    Position(2, 0),
                    Position(3, 0)
                )
            )
        ).isEqualTo(10)

        assertThat(
            perimeterOfRegion(
                setOf(
                    Position(0, 0),
                    Position(1, 0),
                    Position(0, 1),
                    Position(1, 1)
                )
            )
        ).isEqualTo(8)

        assertThat(
            perimeterOfRegion(
                setOf(
                    Position(0, 0),
                    Position(0, 1),
                    Position(1, 1),
                    Position(1, 2)
                )
            )
        ).isEqualTo(10)

        assertThat(
            perimeterOfRegion(
                setOf(
                    Position(0, 0)
                )
            )
        ).isEqualTo(4)
    }

    @Test
    fun testSidesOfRegion() {
        assertThat(
            sidesOfRegion(
                setOf(
                    Position(0, 0),
                    Position(1, 0),
                    Position(2, 0),
                    Position(3, 0)
                )
            )
        ).isEqualTo(4)

        assertThat(
            sidesOfRegion(
                setOf(
                    Position(0, 0),
                    Position(1, 0),
                    Position(0, 1),
                    Position(1, 1)
                )
            )
        ).isEqualTo(4)

        assertThat(
            sidesOfRegion(
                setOf(
                    Position(0, 0),
                    Position(0, 1),
                    Position(1, 1),
                    Position(1, 2)
                )
            )
        ).isEqualTo(8)

        assertThat(
            sidesOfRegion(
                setOf(
                    Position(0, 0)
                )
            )
        ).isEqualTo(4)
    }

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2024/day12_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(1930)
    }

    @Test
    fun testComputePart2() {
        val input = object {}.javaClass.getResource("/2024/day12_test_input.txt")!!.readText()
        assertThat(computePart2(input)).isEqualTo(1206)
    }

}
