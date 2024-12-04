package cz.frais.aoc.year2015.day03

import cz.frais.aoc.structures.Vector

enum class Direction(val symbol: Char, val vector: Vector) {

    NORTH('^', Vector(0, -1)),
    SOUTH('v', Vector(0, 1)),
    EAST('>', Vector(1, 0)),
    WEST('<', Vector(-1, 0));

    companion object {
        fun fromChar(symbol: Char): Direction {
            return Direction.entries.find { it.symbol == symbol }
                ?: throw IllegalArgumentException("Unknown direction symbol: $symbol")
        }
    }

}
