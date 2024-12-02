package cz.frais.aoc.year2023.day10

internal data class MoveVector(val diffX: Int, val diffY: Int) {

    fun apply(position: Position): Position {
        return Position(
            position.x + diffX,
            position.y + diffY
        )
    }

}
