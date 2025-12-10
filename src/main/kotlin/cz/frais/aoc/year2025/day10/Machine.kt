package cz.frais.aoc.year2025.day10

data class Machine(
    val diagram: List<Boolean>,
    val wiring: List<List<Int>>,
    val joltageRequirements: List<Int>
) {

    companion object {

        fun fromString(input: String): Machine {
            val lineSplit = input.split(Regex("\\s+"))

            return Machine(
                diagram = lineSplit.first().drop(1).dropLast(1).map { char -> char == '#' },
                wiring = lineSplit.drop(1).dropLast(1).map { schematicString ->
                    schematicString.drop(1).dropLast(1).split(",").map { it.toInt() }
                },
                joltageRequirements = lineSplit.last().drop(1).dropLast(1).split(",").map { it.toInt() }
            )
        }

    }

}
