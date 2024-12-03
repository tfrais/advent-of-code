package cz.frais.aoc

internal data class Vector(val diffX: Int, val diffY: Int) {

    fun apply(position: Position): Position {
        return Position(
            position.x + diffX,
            position.y + diffY
        )
    }

}
