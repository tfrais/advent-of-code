package cz.frais.aoc.year2023.day05

internal class Almanac(rawAlmanac: String) {

    companion object {
        private val MAP_HEADER_REGEX: Regex = """(?<source>\w+)-to-(?<destination>\w+)\smap:""".toRegex()
        private val MAP_ITEM_REGEX: Regex =
            """(?<destRangeStart>\d+)\s+(?<sourceRangeStart>\d+)\s+(?<length>\d+)""".toRegex()
    }

    var elements: Set<Long>
    var elementCategory: String
    var map: Set<AlmanacMapEntry>

    init {
        val rawAlmanacLines = rawAlmanac.split("\n").filter { s -> s.isNotBlank() }
        val firstLineSplit = rawAlmanacLines.first().split(":")
        val elementCategory = firstLineSplit.first().trim()
        this.elementCategory = if (elementCategory == "seeds") "seed" else elementCategory
        this.elements = firstLineSplit[1].split(" ")
            .filter { s -> s.isNotBlank() }
            .map { it.trim().toLong() }
            .toSet()
        this.map = parseMap(rawAlmanacLines.drop(1))
    }

    private fun parseMap(rawLines: List<String>): Set<AlmanacMapEntry> {
        val result = mutableSetOf<AlmanacMapEntry>()
        var sourceElementCategory = ""
        var destinationElementCategory = ""
        for (line in rawLines) {
            val mapHeaderMatchResult = MAP_HEADER_REGEX.find(line)
            if (mapHeaderMatchResult != null) {
                sourceElementCategory = mapHeaderMatchResult.groups["source"]!!.value
                destinationElementCategory = mapHeaderMatchResult.groups["destination"]!!.value
            } else {
                check(sourceElementCategory.isNotBlank() && destinationElementCategory.isNotBlank()) {
                    "Map header could not be found or parsed"
                }
                val mapItemMatchResult = MAP_ITEM_REGEX.find(line)
                check(mapItemMatchResult != null) {
                    "Could not parse this map item line: $line"
                }
                result.add(
                    AlmanacMapEntry(
                        sourceElementCategory,
                        destinationElementCategory,
                        mapItemMatchResult.groups["destRangeStart"]!!.value.toLong(),
                        mapItemMatchResult.groups["sourceRangeStart"]!!.value.toLong(),
                        mapItemMatchResult.groups["length"]!!.value.toLong()
                    )
                )
            }
        }
        return result
    }

    data class AlmanacMapEntry(
        val sourceElementCategory: String,
        val destinationElementCategory: String,
        val destinationRangeStart: Long,
        val sourceRangeStart: Long,
        val rangeLength: Long
    )

}