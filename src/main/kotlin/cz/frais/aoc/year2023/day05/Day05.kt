package cz.frais.aoc.year2023.day05

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

internal fun calculatePart1(almanac: Almanac, finalCategory: String): Long {
    var currentElements = almanac.initialElements
    var currentElementCategory = almanac.initialElementCategory
    while (currentElementCategory != finalCategory) {
        currentElements = currentElements
            .map { almanac.getDestinationElement(currentElementCategory, it ) }
            .toSet()
        currentElementCategory = almanac.getDestinationCategory(currentElementCategory)
    }
    return currentElements.min()
}

fun main() {
    val almanac = Almanac(object {}.javaClass.getResource("/2023/day05_input.txt")!!.readText())

    logger.info { "Part 1 result is ${calculatePart1(almanac, "location")}." }
}
