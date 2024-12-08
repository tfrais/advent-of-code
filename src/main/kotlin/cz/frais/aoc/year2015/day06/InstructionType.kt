package cz.frais.aoc.year2015.day06

enum class InstructionType(val stringRepresentation: String) {

    TURN_ON("turn on"),
    TURN_OFF("turn off"),
    TOGGLE("toggle");

    companion object {
        fun fromString(stringRepresentation: String): InstructionType {
            return InstructionType.entries.find { it.stringRepresentation == stringRepresentation }
                ?: throw IllegalArgumentException("Unknown instruction string: $stringRepresentation")
        }
    }

}
