package cz.frais.aoc.year2024.day24

enum class Operation(val code: String, val function: (Boolean, Boolean) -> Boolean) {

    AND("AND", { i1, i2 -> i1 && i2 }),
    OR("OR", { i1, i2 -> i1 || i2 }),
    XOR("XOR", { i1, i2 -> i1.xor(i2) });

    companion object {
        fun fromCode(code: String): Operation {
            return Operation.entries.find { it.code == code }
                ?: throw IllegalArgumentException("Unknown operation code: $code")
        }
    }

}
