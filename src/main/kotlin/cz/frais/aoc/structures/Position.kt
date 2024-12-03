package cz.frais.aoc.structures

import kotlin.math.abs

data class Position(val x: Int, val y: Int) {

    fun distanceTo(destinationPosition: Position): Int {
        return abs(destinationPosition.x - this.x) + abs(destinationPosition.y - this.y)
    }

}
