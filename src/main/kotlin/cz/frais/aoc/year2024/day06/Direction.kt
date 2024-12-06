package cz.frais.aoc.year2024.day06

import cz.frais.aoc.structures.Vector

enum class Direction(val vector: Vector) {

    NORTH(Vector(0, -1)),
    EAST(Vector(1, 0)),
    SOUTH(Vector(0, 1)),
    WEST(Vector(-1, 0));

    companion object {

        fun directionAfterTurningRight(direction: Direction): Direction {
            return when (direction) {
                NORTH -> EAST
                EAST -> SOUTH
                SOUTH -> WEST
                WEST -> NORTH
            }
        }

    }

}
