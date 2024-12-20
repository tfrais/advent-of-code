package cz.frais.aoc.year2024.day17

import java.util.*

data class Machine(
    val registers: MutableMap<Register, Long>,
    val program: List<Byte>,
) {
    var instructionPointer = 0
    val output = LinkedList<Long>()

    fun getRegisterValue(register: Register): Long =
        registers[register] ?: error("Could not retrieve value for $register")

    @Suppress("MagicNumber")
    private fun computeOperand(value: Byte, isCombo: Boolean): Long =
        if (!isCombo) value.toLong()
        else when (value) {
            in 0..3 -> value.toLong()
            4.toByte() -> getRegisterValue(Register.A)
            5.toByte() -> getRegisterValue(Register.B)
            6.toByte() -> getRegisterValue(Register.C)
            else -> error("Invalid operand: $value")
        }

    private fun applyNextInstruction() {
        val instruction = Instruction.entries.single { it.opcode == program[instructionPointer] }
        val operand = computeOperand(program[instructionPointer + 1], instruction.hasComboOperand)

        val previousInstructionPointer = instructionPointer
        instruction.function(this, operand)

        // if the instruction itself hasn't caused jump
        if (instructionPointer == previousInstructionPointer) instructionPointer += 2
    }

    fun applyAllInstructions() {
        while (instructionPointer < program.size) {
            applyNextInstruction()
        }
    }

}
