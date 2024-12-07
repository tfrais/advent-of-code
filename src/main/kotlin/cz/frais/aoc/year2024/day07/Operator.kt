package cz.frais.aoc.year2024.day07

enum class Operator(val operation: (Long, Long) -> Long) {

    ADD({ a, b -> a + b }),
    MULTIPLY({ a, b -> a * b }),
    CONCAT({ a, b -> (a.toString() + b.toString()).toLong() })

}
