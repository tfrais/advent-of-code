package cz.frais.aoc.year2024.day13

import cz.frais.aoc.AdventOfCodeDaySolution
import cz.frais.aoc.structures.Position

object Year2024Day13 : AdventOfCodeDaySolution {

    private const val MAX_STEPS_PART1 = 200

    private fun buttonsPrice(buttonsA: Int, buttonsB: Int) =
        buttonsA * Button.BUTTON_A.price + buttonsB * Button.BUTTON_B.price

    private fun buttonsDestination(buttonsA: Int, buttonsB: Int, machine: Machine) =
        Position(
            x = machine.buttonVectors[Button.BUTTON_A]!!.diffX * buttonsA +
                    machine.buttonVectors[Button.BUTTON_B]!!.diffX * buttonsB,
            y = machine.buttonVectors[Button.BUTTON_A]!!.diffY * buttonsA +
                    machine.buttonVectors[Button.BUTTON_B]!!.diffY * buttonsB
        )

    fun leastPrice(machine: Machine, maxSteps: Int? = null): Long? {
        var steps = 1
        while (true) {
            if (steps > (maxSteps ?: Int.MAX_VALUE)) {
                return null
            }

            var buttonsB = steps
            var buttonsA = 0

            val minHopX = machine.buttonVectors.values.map { vector -> vector.diffX }.min()
            val minHopY = machine.buttonVectors.values.map { vector -> vector.diffY }.min()

            if (minHopX * steps > machine.prizePosition.x || minHopY * steps > machine.prizePosition.y) {
                return null
            }

            repeat(steps) {
                if (buttonsDestination(buttonsA, buttonsB, machine) == machine.prizePosition) {
                    return buttonsPrice(buttonsA, buttonsB).toLong()
                }
                buttonsB--
                buttonsA++
            }

            steps++
        }
    }

    override fun computePart1(input: String): Long {
        return Parser.parse(input)
            .sumOf { machine -> leastPrice(machine, MAX_STEPS_PART1) ?: 0 }
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}
