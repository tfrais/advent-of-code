package cz.frais.aoc.year2024.day13

import cz.frais.aoc.structures.Position
import cz.frais.aoc.structures.Vector
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2024Day13Test {

    @Test
    fun testParser() {
        val input = object {}.javaClass.getResource("/2024/day13_test_input.txt")!!.readText()
        val parsedMachines = Parser.parse(input)
        assertThat(parsedMachines).hasSize(4)
        assertThat(parsedMachines.last()).usingRecursiveComparison().isEqualTo(
            Machine(
                mapOf(
                    Button.BUTTON_A to Vector(69, 23),
                    Button.BUTTON_B to Vector(27, 71)
                ),
                Position(18641, 10279)
            )
        )
    }

}
