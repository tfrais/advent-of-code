package cz.frais.aoc.year2024.day06

enum class MapElement(val symbol: Char, val direction: Direction?) {

    EMPTY('.', null),
    OBSTACLE('#', null),
    CURRENT_POSITION_FACING_NORTH('^', Direction.NORTH),
    CURRENT_POSITION_FACING_EAST('>', Direction.EAST),
    CURRENT_POSITION_FACING_SOUTH('v', Direction.SOUTH),
    CURRENT_POSITION_FACING_WEST('<', Direction.WEST);

    companion object {
        fun fromChar(symbol: Char): MapElement {
            return MapElement.entries.find { it.symbol == symbol }
                ?: throw IllegalArgumentException("Unknown map element symbol: $symbol")
        }
    }

}
