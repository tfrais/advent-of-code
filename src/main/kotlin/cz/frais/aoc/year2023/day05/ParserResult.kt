package cz.frais.aoc.year2023.day05

data class ParserResult(
    val initialElementCategory: String,
    val initialElementRanges: List<LongRange>,
    val almanac: Almanac,
)
