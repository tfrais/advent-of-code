package cz.frais.aoc.year2024.day17

object Parser {

    private val REGISTER_RE = Regex("""^Register\s+(?<name>\w+):\s+(?<value>\d+)$""")
    private val PROGRAM_RE = Regex("""^Program:\s+(?<program>.*)$""")

    fun parse(input: String): Machine {
        val (registerLines, programLine) = input.lines()
            .let { it.subList(0, it.indexOf("")) to it.last() }

        val registers = registerLines.associate {
            REGISTER_RE.find(it)!!.let { match ->
                Register.valueOf(match.groups["name"]!!.value) to match.groups["value"]!!.value.toLong()
            }
        }

        val program = PROGRAM_RE.find(programLine)!!.groups["program"]!!
            .value.split(",").map { it.trim().toByte() }

        return Machine(registers.toMutableMap(), program)
    }

}
