package cz.frais.aoc.year2015.day12

import cz.frais.aoc.AdventOfCodeDaySolution
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.longOrNull

object Year2015Day12 : AdventOfCodeDaySolution {

    override fun computePart1(input: String): Long {
        return Regex("-?\\d+").findAll(input).map { it.value.toLong() }.fold(0) { a, b -> a + b }
    }

    fun computePart2(jsonElement: JsonElement): Long =
        when (jsonElement) {
            is JsonArray -> jsonElement.sumOf { computePart2(it) }

            is JsonObject -> {
                if (jsonElement.values.any { it is JsonPrimitive && it.content == "red" }) {
                    0L
                } else {
                    jsonElement.values.sumOf { computePart2(it) }
                }
            }

            is JsonPrimitive -> jsonElement.longOrNull ?: 0L
        }

    override fun computePart2(input: String): Long = computePart2(Json.parseToJsonElement(input))

}
