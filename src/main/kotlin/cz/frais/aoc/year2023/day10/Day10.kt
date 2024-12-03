package cz.frais.aoc.year2023.day10

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

fun calculatePart1(plan: Plan): Int {
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

fun calculatePart2(plan: Plan): Int {
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

fun main() {
    val plan = Plan(object {}.javaClass.getResource("/2023/day10_input.txt")!!.readText())
    logger.info { "Part 1 result is ${calculatePart1(plan)}." }
    logger.info { "Part 2 result is ${calculatePart2(plan)}." }
}
