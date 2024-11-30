package cz.frais.aoc.year2023.day07

internal enum class Card(val code: Char) {

    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    TEN('T'),
    JACK('J'),
    QUEEN('Q'),
    KING('K'),
    ACE('A');

    fun strength(): Int {
        return ordinal
    }

    companion object {
        fun fromChar(code: Char): Card {
            return entries.find { it.code == code }
                ?: throw IllegalArgumentException("Unknown card code: $code")
        }
    }

}
