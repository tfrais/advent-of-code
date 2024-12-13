package cz.frais.aoc.year2024.day13

import cz.frais.aoc.AdventOfCodeDaySolution
import cz.frais.aoc.structures.Position

object Year2024Day13 : AdventOfCodeDaySolution {

    private const val MAX_BUTTONS = 200

    private fun buttonsPrice(buttons: List<Button>) = buttons.sumOf { it.price }

    private fun buttonsDestination(buttons: List<Button>, machine: Machine): Position =
        buttons.fold(Position(0, 0)) { position, button ->
            machine.buttonVectors[button]!!.apply(position)
        }

    fun leastPrice(machine: Machine): Long? {
        for (steps in 1..MAX_BUTTONS) {
            val buttonCombination = MutableList(steps) { Button.BUTTON_B }
            for (i in buttonCombination.indices) {
                if (buttonsDestination(buttonCombination, machine) == machine.prizePosition) {
                    return buttonsPrice(buttonCombination).toLong()
                }
                buttonCombination[i] = Button.BUTTON_A
            }
        }
        return null
    }

    override fun computePart1(input: String): Long {
        return Parser.parse(input)
            .sumOf { machine -> leastPrice(machine) ?: 0 }
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}
