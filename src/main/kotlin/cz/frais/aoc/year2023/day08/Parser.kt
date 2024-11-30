package cz.frais.aoc.year2023.day08

internal class Parser {

    companion object {
        private val NODE_ELEMENT_REGEX: Regex =
            """(?<node>\w+)\s+=\s+\((?<left>\w+),\s+(?<right>\w+)\)""".toRegex()
    }

    fun parse(input: String): Document {
        val lines = input.split("\n").filter { it.isNotBlank() }
        val nodeMap = mutableMapOf<String, Pair<String, String>>()

        for (line in lines.drop(1)) {
            val matchResult = NODE_ELEMENT_REGEX.find(line)
            check(matchResult != null) {
                "Could not parse line $line"
            }
            nodeMap[matchResult.groups["node"]!!.value] = Pair(
                matchResult.groups["left"]!!.value,
                matchResult.groups["right"]!!.value
            )
        }

        return Document(lines[0].toCharArray().toList(), nodeMap)
    }

}
