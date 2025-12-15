package cz.frais.aoc.year2015.day13

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day13 : AdventOfCodeDaySolution {

    private val PARSING_REGEX = Regex("""^(\w+) would (\w+) (\d+) happiness units by sitting next to (\w+).$""")

    private fun prepareMap(input: String): Map<Pair<String, String>, Int> {
        val map = mutableMapOf<Pair<String, String>, Int>()
        for (line in input.lines()) {
            val matchResult = PARSING_REGEX.find(line) ?: throw IllegalArgumentException("Cannot parse: $input")
            val personA = matchResult.groupValues[1]
            val verb = matchResult.groupValues[2]
            val happiness = matchResult.groupValues[3].toInt()
            val personB = matchResult.groupValues[4]

            map[personA to personB] = if (verb == "gain") happiness else -1 * happiness
        }
        return map.toMap()
    }

    private fun distinctPeople(map: Map<Pair<String, String>, Int>): Set<String> =
        map.keys.map { it.first }.union(map.keys.map { it.second })

    private fun <T> permutations(collection: Collection<T>): Set<List<T>> {
        if (collection.size < 2) return setOf(collection.toList())

        return collection.flatMap { element ->
            permutations(collection - element).map { perm -> listOf(element) + perm }
        }.toSet()
    }

    private fun computeHappiness(sittingOrder: List<String>, map: Map<Pair<String, String>, Int>): Int {
        var happiness = 0
        for (i in sittingOrder.indices) {
            // left
            happiness += if (i > 0) {
                map[sittingOrder[i] to sittingOrder[i - 1]]!!
            } else {
                map[sittingOrder[i] to sittingOrder.last()]!!
            }
            // right
            happiness += if (i < sittingOrder.size - 1) {
                map[sittingOrder[i] to sittingOrder[i + 1]]!!
            } else {
                map[sittingOrder[i] to sittingOrder.first()]!!
            }
        }
        return happiness
    }

    private fun computeMax(map: Map<Pair<String, String>, Int>): Long {
        var max = Int.MIN_VALUE
        for (sittingOrder in permutations(distinctPeople(map))) {
            val happiness = computeHappiness(sittingOrder, map)
            if (happiness > max) {
                max = happiness
            }
        }
        return max.toLong()
    }

    override fun computePart1(input: String): Long = computeMax(prepareMap(input))

    override fun computePart2(input: String): Long {
        val map = prepareMap(input).toMutableMap()
        distinctPeople(map).forEach { name ->
            map["myself" to name] = 0
            map[name to "myself"] = 0
        }
        return computeMax(map.toMap())
    }

}
