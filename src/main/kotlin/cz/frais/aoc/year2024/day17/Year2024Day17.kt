package cz.frais.aoc.year2024.day17

object Year2024Day17 {

    fun computePart1(input: String): String {
        val machine = Parser.parse(input)
        machine.applyAllInstructions()
        return machine.output.joinToString(",")
    }

}
