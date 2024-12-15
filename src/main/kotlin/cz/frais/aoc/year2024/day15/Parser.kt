package cz.frais.aoc.year2024.day15

import cz.frais.aoc.structures.Table

object Parser {

    fun parse(input: String): Sokoban {
        val lines = input.lines()
        val emptyIndex = lines.indexOf("")

        val mapLines = lines.subList(0, emptyIndex)
        val map = Table(mapLines.joinToString("\n")) { MapElement.fromChar(it) }

        val directions = lines.subList(emptyIndex + 1, lines.size)
            .joinToString("")
            .map { Direction.fromChar(it) }

        return Sokoban(
            map,
            directions,
            map.findValue(MapElement.START).single()
        )

    }

}
