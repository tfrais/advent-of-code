package cz.frais.aoc.year2023.day08

import cz.frais.aoc.year2023.day08.Year2023Day08.walk
import cz.frais.aoc.year2023.day08.Year2023Day08.walkGhost
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2023Day08Test {

    private val parser = Parser()

    @Test
    fun testParse() {
        val input = object {}.javaClass.getResource("/2023/day08_test_input1.txt")!!.readText()
        val document = parser.parse(input)
        assertThat(document.instructions).usingRecursiveComparison()
            .isEqualTo(listOf('R', 'L'))
        assertThat(document.nodeMap).hasSize(7)
        assertThat(document.nodeMap["AAA"]).usingRecursiveComparison()
            .isEqualTo(Pair("BBB", "CCC"))
    }

    @Test
    fun testWalkForInputFile1() {
        val input = object {}.javaClass.getResource("/2023/day08_test_input1.txt")!!.readText()
        assertThat(walk(parser.parse(input))).isEqualTo(2)
    }

    @Test
    fun testWalkForInputFile2() {
        val input = object {}.javaClass.getResource("/2023/day08_test_input2.txt")!!.readText()
        assertThat(walk(parser.parse(input))).isEqualTo(6)
    }

    @Test
    fun testWalkGhostForInputFile() {
        val input = object {}.javaClass.getResource("/2023/day08_test_input_part2.txt")!!.readText()
        assertThat(walkGhost(parser.parse(input))).isEqualTo(6)
    }

}
