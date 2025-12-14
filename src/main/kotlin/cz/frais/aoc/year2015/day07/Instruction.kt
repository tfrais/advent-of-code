package cz.frais.aoc.year2015.day07

enum class Instruction(
    val regex: Regex,
    val operation: (MatchResult, MutableMap<String, UShort>) -> Boolean // return whether the operation was executed
) {

    CONSTANT(
        Regex("^(\\d+)\\s+->\\s+(\\w+)$"),
        { matchResult, wireValues ->
            wireValues[matchResult.groupValues[2]] = matchResult.groupValues[1].toUShort()
            true
        }
    ),
    AND(
        Regex("^(\\w+)\\s+AND\\s+(\\w+)\\s+->\\s+(\\w+)$"),
        { matchResult, wireValues ->
            val operand1 = matchResult.groupValues[1]
            val operand2 = matchResult.groupValues[2]
            if (operand1 !in wireValues || operand2 !in wireValues) {
                false
            } else {
                wireValues[matchResult.groupValues[3]] =
                    wireValues[matchResult.groupValues[1]]!! and wireValues[matchResult.groupValues[2]]!!
                true
            }
        }
    ),
    AND_CONSTANT(
        Regex("^(\\d+)\\s+AND\\s+(\\w+)\\s+->\\s+(\\w+)$"),
        { matchResult, wireValues ->
            val operand = matchResult.groupValues[2]
            if (operand !in wireValues) {
                false
            } else {
                wireValues[matchResult.groupValues[3]] =
                    wireValues[matchResult.groupValues[2]]!! and matchResult.groupValues[1].toUShort()
                true
            }
        }
    ),
    OR(
        Regex("^(\\w+)\\s+OR\\s+(\\w+)\\s+->\\s+(\\w+)$"),
        { matchResult, wireValues ->
            val operand1 = matchResult.groupValues[1]
            val operand2 = matchResult.groupValues[2]
            if (operand1 !in wireValues || operand2 !in wireValues) {
                false
            } else {
                wireValues[matchResult.groupValues[3]] =
                    wireValues[matchResult.groupValues[1]]!! or wireValues[matchResult.groupValues[2]]!!
                true
            }
        }
    ),
    OR_CONSTANT(
        Regex("^(\\d+)\\s+OR\\s+(\\w+)\\s+->\\s+(\\w+)$"),
        { matchResult, wireValues ->
            val operand = matchResult.groupValues[2]
            if (operand !in wireValues) {
                false
            } else {
                wireValues[matchResult.groupValues[3]] =
                    wireValues[matchResult.groupValues[2]]!! or matchResult.groupValues[1].toUShort()
                true
            }
        }
    ),
    LSHIFT(
        Regex("^(\\w+)\\s+LSHIFT\\s+(\\d+)\\s+->\\s+(\\w+)$"),
        { matchResult, wireValues ->
            val operand = matchResult.groupValues[1]
            val shiftAmount = matchResult.groupValues[2].toInt()
            if (operand !in wireValues) {
                false
            } else {
                wireValues[matchResult.groupValues[3]] = (wireValues[matchResult.groupValues[1]]!!.toUInt() shl shiftAmount).toUShort()
                true
            }
        }
    ),
    RSHIFT(
        Regex("^(\\w+)\\s+RSHIFT\\s+(\\d+)\\s+->\\s+(\\w+)$"),
        { matchResult, wireValues ->
            val operand = matchResult.groupValues[1]
            val shiftAmount = matchResult.groupValues[2].toInt()
            if (operand !in wireValues) {
                false
            } else {
                wireValues[matchResult.groupValues[3]] = (wireValues[matchResult.groupValues[1]]!!.toUInt() shr shiftAmount).toUShort()
                true
            }
        }
    ),
    NOT(
        Regex("^NOT\\s+(\\w+)\\s+->\\s+(\\w+)$"),
        { matchResult, wireValues ->
            val operand = matchResult.groupValues[1]
            if (operand !in wireValues) {
                false
            } else {
                wireValues[matchResult.groupValues[2]] = wireValues[matchResult.groupValues[1]]!!.inv()
                true
            }
        }
    ),

}