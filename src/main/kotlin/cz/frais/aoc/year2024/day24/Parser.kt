package cz.frais.aoc.year2024.day24

object Parser {

    private val WIRE_RE = Regex("""^(?<name>\w+):\s+(?<value>\d)$""")
    private val GATE_RE = Regex(
        """^(?<input1>\w+)\s+(?<operation>\w+)\s+(?<input2>\w+)\s+->\s+(?<output>\w+)$"""
    )


    fun parse(input: String): System {
        val lines = input.lines()
        val emptyIndex = lines.indexOf("")
        val wireLines = lines.subList(0, emptyIndex)
        val gateLines = lines.subList(emptyIndex + 1, lines.size)

        val wires = wireLines.associate {
            WIRE_RE.find(it)!!.let { match ->
                match.groups["name"]!!.value to (match.groups["value"]!!.value == "1")
            }
        }

        val gates = gateLines.map {
            GATE_RE.find(it)!!.let { match ->
                Gate(
                    match.groups["input1"]!!.value,
                    match.groups["input2"]!!.value,
                    Operation.fromCode(match.groups["operation"]!!.value),
                    match.groups["output"]!!.value
                )
            }
        }

        return System(wires.toMutableMap(), gates.toMutableList())
    }
}
