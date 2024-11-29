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
        detectNumberResult.firstPosition + detectNumberResult.length + 1,
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

fun main() {
    val content = object {}.javaClass.getResource("/2023/day03_input.txt")!!.readText()
    val result = computeSumOfNumbersAdjacentToSymbol(Plan(content))
    logger.info { "Result is $result." }
}
