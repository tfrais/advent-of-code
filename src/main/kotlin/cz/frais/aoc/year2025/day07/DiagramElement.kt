package cz.frais.aoc.year2025.day07

enum class DiagramElement(val symbol: Char) {

    EMPTY('.'),
    START('S'),
    SPLITTER('^');

    companion object {
        fun fromChar(symbol: Char): DiagramElement {
            return DiagramElement.entries.find { it.symbol == symbol }
                ?: throw IllegalArgumentException("Unknown diagram element symbol: $symbol")
        }
    }

}
