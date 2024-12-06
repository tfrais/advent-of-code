package cz.frais.aoc.year2024.day06

import cz.frais.aoc.structures.Position
import cz.frais.aoc.structures.Table

class MapWalkthrough(input: String) {

    val table: Table<MapElement> = Table(input) { MapElement.fromChar(it) }
    var currentPosition: Position
    var currentDirection: Direction
    val visitedPositionDirection: MutableList<Pair<Position, Direction>>

    init {
        this.currentPosition = table.findValue(MapElement.CURRENT_POSITION_FACING_NORTH).single()
        this.currentDirection = table.valueAt(currentPosition).direction!!
        this.visitedPositionDirection = mutableListOf(Pair(currentPosition, currentDirection))
    }

    enum class MoveResult { OK, EXIT, LOOP }

    fun move(): MoveResult {
        val nextPosition = currentDirection.vector.apply(currentPosition)

        if (!table.inTable(nextPosition)) return MoveResult.EXIT

        if (table.valueAt(nextPosition) == MapElement.OBSTACLE) {
            currentDirection = Direction.directionAfterTurningRight(currentDirection)
            return MoveResult.OK
        }

        currentPosition = nextPosition
        if (Pair(currentPosition, currentDirection) in visitedPositionDirection) {
            return MoveResult.LOOP
        }
        visitedPositionDirection.add(Pair(currentPosition, currentDirection))

        return MoveResult.OK
    }

    fun moveTillEndOrLoop(): MoveResult {
        while (true) {
            val moveResult = this.move()
            if (moveResult != MoveResult.OK) return moveResult
        }
    }

}
