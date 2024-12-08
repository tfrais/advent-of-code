package cz.frais.aoc.year2015.day05

@Suppress("MagicNumber")
enum class Rule(val isNice: (String) -> Boolean) {

    THREE_VOWELS({ string ->
        string.count { char -> char in setOf('a', 'e', 'i', 'o', 'u') } >= 3
    }),
    DOUBLE_CHAR({ string ->
        string.zipWithNext { a, b -> a == b }.any { it }
    }),
    FORBIDDEN_SUBSTRINGS({ string ->
        setOf("ab", "cd", "pq", "xy").none { string.contains(it) }
    }),
    TWO_CHARS_TWICE_NO_OVERLAP({ string ->
        string.windowed(2)
            .any { pair -> string.indexOf(pair) + 1 < string.lastIndexOf(pair) }
    }),
    TWO_SAME_CHARS_ANOTHER_IN_BETWEEN({ string ->
        string.windowed(3).any { it[0] == it[2] }
    });

}
