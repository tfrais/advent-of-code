package cz.frais.aoc.year2024.day13

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2024Day13 : AdventOfCodeDaySolution {

    const val MAX_STEPS_PART1 = 100
    const val OFFSET_PART2 = 10000000000000L

    private fun solveA(machine: Machine): Long {
        val buttonA = machine.buttonVectors[Button.BUTTON_A]!!
        val buttonB = machine.buttonVectors[Button.BUTTON_B]!!
        return (machine.priceY * buttonB.diffX - machine.priceX * buttonB.diffY) /
                (buttonA.diffY * buttonB.diffX - buttonA.diffX * buttonB.diffY)
    }

    private fun solveB(machine: Machine): Long {
        val buttonA = machine.buttonVectors[Button.BUTTON_A]!!
        val buttonB = machine.buttonVectors[Button.BUTTON_B]!!
        return (machine.priceX - solveA(machine) * buttonA.diffX) / buttonB.diffX
    }

    fun leastPrice(machine: Machine, maxSteps: Int? = null, offset: Long = 0): Long? {
        val buttonA = machine.buttonVectors[Button.BUTTON_A]!!
        val buttonB = machine.buttonVectors[Button.BUTTON_B]!!

        val machineWithOffset = Machine(
            machine.buttonVectors,
            machine.priceX + offset,
            machine.priceY + offset
        )
        val buttonsA = solveA(machineWithOffset)
        val buttonsB = solveB(machineWithOffset)

        if (buttonsA * buttonA.diffX + buttonsB * buttonB.diffX != machineWithOffset.priceX) {
            return null
        }
        if (buttonsA * buttonA.diffY + buttonsB * buttonB.diffY != machineWithOffset.priceY) {
            return null
        }

        if (buttonsA < 0 || buttonsB < 0) {
            return null
        }
        if (maxSteps != null && (buttonsA > maxSteps || buttonsB > maxSteps)) {
            return null
        }
        return buttonsA * Button.BUTTON_A.price + buttonsB * Button.BUTTON_B.price
    }

    override fun computePart1(input: String): Long {
        return Parser.parse(input)
            .sumOf { machine -> leastPrice(machine, MAX_STEPS_PART1) ?: 0 }
    }

    override fun computePart2(input: String): Long {
        return Parser.parse(input)
            .sumOf { machine -> leastPrice(machine, null, OFFSET_PART2) ?: 0 }
    }

}
