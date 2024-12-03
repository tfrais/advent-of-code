package cz.frais.aoc.year2023.day10

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2023Day10 : AdventOfCodeDaySolution {

    fun computePart1(plan: Plan): Int {
        return PipeCalculator.pipe(plan).size / 2
    }

    fun IntRange.isInOpenEndedRange(value: Int): Boolean =
        value > this.first && value < this.last

    fun <T> createGroupedMap(
        pipe: List<T>,
        groupBySelector: (T) -> Int,
        mapValueSelector: (T) -> Int,
    ): Map<Int, List<IntRange>> {
        return pipe.groupBy(groupBySelector)
            .mapValues {
                it.value.map(mapValueSelector)
                    .sorted()
                    .chunked(2)
                    .map { chunk -> IntRange(chunk.first(), chunk.last()) }
            }
    }

    fun computePart2(plan: Plan): Int {
        val pipe = PipeCalculator.pipe(plan)

        val yMap = createGroupedMap(pipe, { it.y }, { it.x })
        val xMap = createGroupedMap(pipe, { it.x }, { it.y })
        var enclosedTiles = 0

        for (x in pipe.minOf { it.x }..<pipe.maxOf { it.x }) {
            for (y in pipe.minOf { it.y }..<pipe.maxOf { it.y }) {
                if (yMap[y]?.any { it.isInOpenEndedRange(x) } == true
                    && xMap[x]?.any { it.isInOpenEndedRange(y) } == true) {
                    enclosedTiles += 1
                }
            }
        }

        return enclosedTiles
    }

    override fun computePart1(input: String): Long {
        return computePart1(Plan(input)).toLong()
    }

    override fun computePart2(input: String): Long {
        return computePart2(Plan(input)).toLong()
    }

}
