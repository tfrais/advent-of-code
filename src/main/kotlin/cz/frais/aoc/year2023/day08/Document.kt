package cz.frais.aoc.year2023.day08

data class Document(
    val instructions: List<Char>,
    var nodeMap: Map<String, Pair<String, String>>
)
