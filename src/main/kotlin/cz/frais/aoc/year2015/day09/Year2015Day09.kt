package cz.frais.aoc.year2015.day09

import cz.frais.aoc.AdventOfCodeDaySolution
import kotlin.to

object Year2015Day09 : AdventOfCodeDaySolution {

    private data class CityDistance(
        val from: String,
        val to: String,
        val distance: Int
    ) {

        companion object {

            private val PARSING_REGEX = Regex("""^(\w+)\s+to\s+(\w+)\s+=\s+(\d+)$""")

            fun fromString(input: String): CityDistance {
                val matchResult = PARSING_REGEX.find(input) ?: throw IllegalArgumentException("Cannot parse: $input")
                return CityDistance(
                    from = matchResult.groupValues[1],
                    to = matchResult.groupValues[2],
                    distance = matchResult.groupValues[3].toInt()
                )
            }

        }

    }

    private fun <T> permutations(collection: Collection<T>): Set<List<T>> {
        if (collection.size < 2) return setOf(collection.toList())

        return collection.flatMap { element ->
            permutations(collection - element).map { perm -> listOf(element) + perm }
        }.toSet()
    }

    private fun prepareMap(input: String): Map<Pair<String, String>, Int> =
        input.lines().map { CityDistance.fromString(it) }.flatMap { cityDistance ->
            listOf(
                cityDistance.from to cityDistance.to to cityDistance.distance,
                cityDistance.to to cityDistance.from to cityDistance.distance
            )
        }.toMap()

    private fun distinctCities(map: Map<Pair<String, String>, Int>): Set<String> =
        map.keys.map { it.first }.union(map.keys.map { it.second })

    private fun pathLength(route: List<String>, map: Map<Pair<String, String>, Int>): Int =
        route.zipWithNext { cityA, cityB -> map[cityA to cityB]!! }.sum()

    override fun computePart1(input: String): Long {
        val map = prepareMap(input)
        var shortest = Int.MAX_VALUE
        for (path in permutations(distinctCities(map))) {
            val length = pathLength(path, map)
            if (length < shortest) {
                shortest = length
            }
        }
        return shortest.toLong()
    }

    override fun computePart2(input: String): Long {
        val map = prepareMap(input)
        var longest = -1
        for (path in permutations(distinctCities(map))) {
            val length = pathLength(path, map)
            if (length > longest) {
                longest = length
            }
        }
        return longest.toLong()
    }

}
