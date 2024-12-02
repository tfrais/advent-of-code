package cz.frais.aoc.year2023.day10

internal enum class Symbol(
    val code: Char,
    val moveVectors: List<MoveVector>
) {

    VERTICAL('|', listOf(MoveVector(0, -1), MoveVector(0, 1))),
    HORIZONTAL('-', listOf(MoveVector(-1, 0), MoveVector(1, 0))),
    NE_BEND('L', listOf(MoveVector(1, 0), MoveVector(0, -1))),
    NW_BEND('J', listOf(MoveVector(-1, 0), MoveVector(0, -1))),
    SE_BEND('F', listOf(MoveVector(1, 0), MoveVector(0, 1))),
    SW_BEND('7', listOf(MoveVector(-1, 0), MoveVector(0, 1))),

    GROUND('.', listOf()),
    START('S', listOf());

    companion object {
        fun fromChar(code: Char): Symbol {
            return Symbol.entries.find { it.code == code }
                ?: throw IllegalArgumentException("Unknown symbol code: $code")
        }
    }

}
