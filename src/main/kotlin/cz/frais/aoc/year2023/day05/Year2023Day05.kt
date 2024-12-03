package cz.frais.aoc.year2023.day05

import cz.frais.aoc.AdventOfCodeDaySolution
import java.util.stream.Collectors

object Year2023Day05 : AdventOfCodeDaySolution {

    private val parser = Parser()

    fun compute(almanac: Almanac, range: LongRange): Long {
        var min = Long.MAX_VALUE
        for (element in range) {
            val finalElement = almanac.getFinalElement(element)
            if (finalElement < min) {
                min = finalElement
            }
        }
        return min
    }

    fun compute(almanac: Almanac, initialElementRanges: List<LongRange>): Long {
        return initialElementRanges.parallelStream()
            .map { range -> compute(almanac, range) }
            .collect(Collectors.minBy(Comparator.naturalOrder()))
            .orElse(0)
    }

    override fun computePart1(input: String): Long {
        val parserResultPart = parser.parse(input, false)
        return compute(parserResultPart.almanac, parserResultPart.initialElementRanges)
    }

    override fun computePart2(input: String): Long {
        val parserResultPart = parser.parse(input, true)
        return compute(parserResultPart.almanac, parserResultPart.initialElementRanges)
    }

}
