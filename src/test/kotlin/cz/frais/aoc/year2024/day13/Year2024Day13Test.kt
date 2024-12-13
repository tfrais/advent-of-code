package cz.frais.aoc.year2024.day13

import cz.frais.aoc.structures.Position
import cz.frais.aoc.structures.Vector
import cz.frais.aoc.year2024.day13.Year2024Day13.computePart1
import cz.frais.aoc.year2024.day13.Year2024Day13.leastPrice
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

    @Test
    fun testLeastPrice() {
        val machine = Machine(
            mapOf(
                Button.BUTTON_A to Vector(94, 34),
                Button.BUTTON_B to Vector(22, 67)
            ),
            Position(8400, 5400)
        )
        assertThat(leastPrice(machine, 200)).isEqualTo(280)
    }

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2024/day13_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(480)
    }

}
