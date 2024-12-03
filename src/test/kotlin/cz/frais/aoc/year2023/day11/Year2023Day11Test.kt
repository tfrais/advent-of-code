package cz.frais.aoc.year2023.day11

import cz.frais.aoc.year2023.day11.Year2023Day11.computeDistanceSum
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2023Day11Test {

    @Test
    fun testSpaceExpanded() {
        assertThat(SpaceParser.parse("#"))
            .isEqualTo(SpaceParser.parse("#").expanded())

        assertThat(SpaceParser.parse("#.."))
            .isEqualTo(SpaceParser.parse("#.").expanded())

        assertThat(SpaceParser.parse("#..#"))
            .isEqualTo(SpaceParser.parse("#.#").expanded())

        assertThat(SpaceParser.parse("#\n.\n."))
            .isEqualTo(SpaceParser.parse("#\n.").expanded())

        assertThat(SpaceParser.parse("#\n.\n.\n#"))
            .isEqualTo(SpaceParser.parse("#\n.\n#").expanded())

        assertThat(SpaceParser.parse("#...#"))
            .isEqualTo(SpaceParser.parse("#.#").expanded(3))

        assertThat(SpaceParser.parse("#\n.\n.\n.\n#"))
            .isEqualTo(SpaceParser.parse("#\n.\n#").expanded(3))
    }

    @Test
    fun testSpaceExpandedForInputFile() {
        val expectedSpace = SpaceParser.parse(
            object {}.javaClass.getResource("/2023/day11_test_input_part1_expanded.txt")!!.readText()
        )
        val actualSpace = SpaceParser.parse(
            object {}.javaClass.getResource("/2023/day11_test_input_part1.txt")!!.readText()
        ).expanded()
        assertThat(actualSpace).usingRecursiveComparison().isEqualTo(expectedSpace)
    }

    @Test
    fun testComputeDistanceSum() {
        assertThat(computeDistanceSum(SpaceParser.parse("#"))).isEqualTo(0)
        assertThat(computeDistanceSum(SpaceParser.parse("##"))).isEqualTo(1)
        assertThat(computeDistanceSum(SpaceParser.parse("#.#"))).isEqualTo(3)
        assertThat(computeDistanceSum(SpaceParser.parse("###"))).isEqualTo(4)
    }

    @Test
    fun testComputeDistanceSumForInputFile() {
        val space = SpaceParser.parse(
            object {}.javaClass.getResource("/2023/day11_test_input_part1.txt")!!.readText()
        )
        assertThat(computeDistanceSum(space)).isEqualTo(374)
    }

    @Test
    fun testCalculatePart2ForInputFile() {
        val space = SpaceParser.parse(
            object {}.javaClass.getResource("/2023/day11_test_input_part1.txt")!!.readText()
        )
        assertThat(computeDistanceSum(space, 10)).isEqualTo(1030)
        assertThat(computeDistanceSum(space, 100)).isEqualTo(8410)
    }

}
