package cz.frais.aoc.year2024.day03

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2024Day03 : AdventOfCodeDaySolution {

    private val MUL_REGEX: Regex = """mul\((?<a>\d{1,3}),(?<b>\d{1,3})\)""".toRegex()
    private const val DO_PHRASE = "do()"
    private const val DONT_PHRASE = "don't()"

    private val multiplyMatchResult: (MatchResult) -> Int = { match ->
        match.groups["a"]!!.value.toInt() * match.groups["b"]!!.value.toInt()
    }

    private val PART2_REGEX: Regex =
        """${MUL_REGEX.pattern}|${Regex.escape(DO_PHRASE)}|${Regex.escape(DONT_PHRASE)}""".toRegex()

    override fun computePart1(input: String): Long {
        return MUL_REGEX.findAll(input)
            .map(multiplyMatchResult)
            .sum().toLong()
    }

    override fun computePart2(input: String): Long {
        var doPhase = true
        var result = 0L
        PART2_REGEX.findAll(input).forEach {
            when (it.value) {
                DO_PHRASE -> doPhase = true
                DONT_PHRASE -> doPhase = false
                else -> result += if (doPhase) multiplyMatchResult(it) else 0
            }
        }
        return result
    }

}