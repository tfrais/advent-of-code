package cz.frais.aoc.year2025.day11

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2025Day11 : AdventOfCodeDaySolution {

    fun loadMap(input: String): Map<String, Set<String>> {
        val resultMap = mutableMapOf<String, Set<String>>()
        for (line in input.lines()) {
            val split = line.split(":")
            resultMap[split[0]] = split[1].trim().split(Regex("\\s+")).toSet()
        }
        return resultMap.toMap()
    }

    fun countPaths(
        map: Map<String, Set<String>>,
        start: String,
        target: String,
        memo: MutableMap<Pair<String, String>, Long> = mutableMapOf()
    ): Long {
        val key = start to target
        memo[key]?.let { return it }

        if (start == target) return 1

        var total = 0L
        for (next in map[start].orEmpty()) {
            total += countPaths(map, next, target, memo)
        }

        memo[key] = total
        return total
    }

    override fun computePart1(input: String): Long {
        val map = loadMap(input)
        val memoize = mutableMapOf<Pair<String, String>, Long>()

        return countPaths(map, "you", "out", memoize)
    }

    override fun computePart2(input: String): Long {
        val map = loadMap(input)
        val memoize = mutableMapOf<Pair<String, String>, Long>()

        return countPaths(map, "svr", "fft", memoize) *
                countPaths(map, "fft", "dac", memoize) *
                countPaths(map, "dac", "out", memoize) +
                countPaths(map, "svr", "dac", memoize) *
                countPaths(map, "dac", "fft", memoize) *
                countPaths(map, "fft", "out", memoize)
    }

}