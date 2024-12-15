package cz.frais.aoc.year2024.day15

import cz.frais.aoc.structures.Position
import cz.frais.aoc.structures.Table

class Sokoban(
    var map: Table<MapElement>,
    private val directions: List<Direction>,
    var currentPosition: Position,
) {

    companion object {
        private const val GPS_Y_DIMENSION_FACTOR = 100L
    }

    fun move(direction: Direction) {
        val nextPosition = direction.vector.apply(currentPosition)
        if (!map.inTable(nextPosition) || map.valueAt(nextPosition) == MapElement.WALL) {
            return
        }
        if (map.valueAt(nextPosition) in listOf(MapElement.EMPTY, MapElement.START)) {
            currentPosition = nextPosition
            return
        }
        pushBoxesAndMove(direction)
    }

    private fun pushBoxesAndMove(direction: Direction) {
        val boxPositions = mutableListOf<Position>()
        var position = direction.vector.apply(currentPosition)
        while (map.valueAt(position) == MapElement.BOX) {
            boxPositions.add(position)
            position = direction.vector.apply(position)
        }
        if (!map.inTable(position) || map.valueAt(position) == MapElement.WALL) {
            return
        }
        val nextPosition = direction.vector.apply(currentPosition)
        map = map.withReplacedValues(
            boxPositions.associate { box -> direction.vector.apply(box) to MapElement.BOX }
                .plus(nextPosition to MapElement.EMPTY)
        )
        currentPosition = nextPosition
    }


    fun moveTillEnd() {
        directions.forEach { move(it) }
    }

    fun computeGpsBoxes(): Long =
        map.findValue(MapElement.BOX).sumOf { it.x + it.y * GPS_Y_DIMENSION_FACTOR }

}
