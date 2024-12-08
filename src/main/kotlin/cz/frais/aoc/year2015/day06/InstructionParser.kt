package cz.frais.aoc.year2015.day06

object InstructionParser {

    private val INSTRUCTION_REGEX = Regex(
        """(?<type>\D+)""" +
                """(?<xStart>\d+),(?<yStart>\d+)""" +
                """\s+through\s+""" +
                """(?<xEnd>\d+),(?<yEnd>\d+)"""
    )

    fun parse(rawInstruction: String): Instruction {
        val matchResult = INSTRUCTION_REGEX.find(rawInstruction)
            ?: error("Could not parse instruction from string '$rawInstruction'")

        return Instruction(
            InstructionType.fromString(matchResult.groups["type"]!!.value.trim()),
            IntRange(matchResult.groups["xStart"]!!.value.toInt(), matchResult.groups["xEnd"]!!.value.toInt()),
            IntRange(matchResult.groups["yStart"]!!.value.toInt(), matchResult.groups["yEnd"]!!.value.toInt())
        )
    }

}
