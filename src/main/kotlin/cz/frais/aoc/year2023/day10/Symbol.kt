package cz.frais.aoc.year2023.day10

import cz.frais.aoc.Vector

enum class Symbol(
    val code: Char,
    val vectors: List<Vector>,
) {

    VERTICAL('|', listOf(Vector(0, -1), Vector(0, 1))),
    HORIZONTAL('-', listOf(Vector(-1, 0), Vector(1, 0))),
    NE_BEND('L', listOf(Vector(1, 0), Vector(0, -1))),
    NW_BEND('J', listOf(Vector(-1, 0), Vector(0, -1))),
    SE_BEND('F', listOf(Vector(1, 0), Vector(0, 1))),
    SW_BEND('7', listOf(Vector(-1, 0), Vector(0, 1))),

    GROUND('.', listOf()),
    START('S', listOf());

    companion object {
        fun fromChar(code: Char): Symbol {
            return Symbol.entries.find { it.code == code }
                ?: throw IllegalArgumentException("Unknown symbol code: $code")
        }
    }

}
