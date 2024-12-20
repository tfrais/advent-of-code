package cz.frais.aoc.year2024.day17

import kotlin.math.pow

@Suppress("MagicNumber")
enum class Instruction(
    val opcode: Byte,
    val hasComboOperand: Boolean,
    val function: (Machine, Long) -> Unit,
) {

    ADV(
        0, true,
        { machine, operand ->
            machine.registers[Register.A] = machine.getRegisterValue(Register.A) / 2.0.pow(operand.toInt()).toLong()
        }
    ),
    BXL(
        1, false,
        { machine, operand ->
            machine.registers[Register.B] = machine.getRegisterValue(Register.B).xor(operand)
        }
    ),
    BST(
        2, true,
        { machine, operand ->
            machine.registers[Register.B] = operand % 8L
        }
    ),
    JNZ(
        3, false,
        { machine, operand ->
            if (machine.getRegisterValue(Register.A) != 0L) machine.instructionPointer = operand.toInt()
        }
    ),
    BXC(
        4, true,
        { machine, _ ->
            machine.registers[Register.B] =
                machine.getRegisterValue(Register.B).xor(machine.getRegisterValue(Register.C))
        }
    ),
    OUT(
        5, true,
        { machine, operand ->
            machine.output += operand % 8L
        }
    ),
    BDV(
        6, true,
        { machine, operand ->
            machine.registers[Register.B] = machine.getRegisterValue(Register.A) / 2.0.pow(operand.toInt()).toLong()
        }
    ),
    CDV(
        7, true,
        { machine, operand ->
            machine.registers[Register.C] = machine.getRegisterValue(Register.A) / 2.0.pow(operand.toInt()).toLong()
        }
    )

}
