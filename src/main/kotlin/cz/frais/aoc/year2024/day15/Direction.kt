package cz.frais.aoc.year2024.day15

import cz.frais.aoc.structures.Vector

enum class Direction(val symbol: Char, val vector: Vector) {

    NORTH('^', Vector(0, -1)),
    EAST('>', Vector(1, 0)),
    SOUTH('v', Vector(0, 1)),
    WEST('<', Vector(-1, 0));

    companion object {
        fun fromChar(symbol: Char): Direction {
            return Direction.entries.find { it.symbol == symbol }
                ?: throw IllegalArgumentException("Unknown direction symbol: $symbol")
        }
    }

}
