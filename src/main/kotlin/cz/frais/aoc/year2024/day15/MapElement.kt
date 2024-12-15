package cz.frais.aoc.year2024.day15

enum class MapElement(val symbol: Char) {

    EMPTY('.'),
    WALL('#'),
    BOX('O'),
    START('@');

    companion object {
        fun fromChar(symbol: Char): MapElement {
            return MapElement.entries.find { it.symbol == symbol }
                ?: throw IllegalArgumentException("Unknown map element symbol: $symbol")
        }
    }

}
