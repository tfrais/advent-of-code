package cz.frais.aoc.year2023.day03

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

internal fun detectFullNumber(plan: Plan, x: Int, y: Int): DetectNumberResult {
    require(plan.get(x, y).isDigit()) { "Function assumes it is called for a cell with digit" }
    var i = x
    while (plan.get(i, y).isDigit()) {
        i--
    }
    i++
    val firstPosition = i
    var number = ""
    while (plan.get(i, y).isDigit()) {
        number += plan.get(i, y)
        i++
    }
    return DetectNumberResult(number.toInt(), firstPosition, y, number.length)
}

internal fun getAdjacentPositions(minX: Int, maxX: Int, minY: Int, maxY: Int): List<Pair<Int, Int>> {
    val resultList: MutableList<Pair<Int, Int>> = mutableListOf()
    for (x in minX..maxX) {
        for (y in minY..maxY) {
            resultList.add(Pair(x, y))
        }
    }
    return resultList
}

internal fun getAdjacentPositions(detectNumberResult: DetectNumberResult): List<Pair<Int, Int>> {
    return getAdjacentPositions(
        detectNumberResult.firstPosition - 1,
        detectNumberResult.firstPosition + detectNumberResult.length,
        detectNumberResult.lineIndex - 1,
        detectNumberResult.lineIndex + 1
    )
}

internal fun isNumberAdjacentToSymbol(plan: Plan, detectNumberResult: DetectNumberResult): Boolean {
    return getAdjacentPositions(detectNumberResult).any { position ->
        plan.containsSymbol(position.first, position.second)
    }
}

internal fun computeSumOfNumbersAdjacentToSymbol(plan: Plan): Int {
    var sum = 0
    for (y in 0..<plan.getHeight()) {
        var x = 0
        while (x < plan.getWidth()) {
            if (plan.containsDigit(x, y)) {
                val detectNumberResult = detectFullNumber(plan, x, y)
                if (isNumberAdjacentToSymbol(plan, detectNumberResult)) {
                    sum += detectNumberResult.number
                }
                x += detectNumberResult.length
            } else {
                x++
            }
        }
    }
    return sum
}

internal fun computeSumOfGearRatios(plan: Plan): Long {
    var sum = 0L
    for (y in 0..<plan.getHeight()) {
        for (x in 0..<plan.getWidth()) {
            if (plan.get(x, y) == '*') {
                val adjacentNumbers = getAdjacentPositions(x - 1, x + 1, y - 1, y + 1)
                    .filter { position -> plan.containsDigit(position.first, position.second) }
                    .map { position -> detectFullNumber(plan, position.first, position.second) }
                    .distinct()
                if (adjacentNumbers.size == 2) {
                    sum += adjacentNumbers[0].number * adjacentNumbers[1].number
                }
            }
        }
    }
    return sum
}

fun main() {
    val content = object {}.javaClass.getResource("/2023/day03_input.txt")!!.readText()
    val plan = Plan(content)

    logger.info { "Part 1 result is ${computeSumOfNumbersAdjacentToSymbol(plan)}." }
    logger.info { "Part 2 result is ${computeSumOfGearRatios(plan)}." }
}
