package cz.frais.aoc.year2023.day05

internal class Almanac(rawAlmanac: String) {

    companion object {
        private val MAP_HEADER_REGEX: Regex = """(?<source>\w+)-to-(?<destination>\w+)\smap:""".toRegex()
        private val MAP_ITEM_REGEX: Regex =
            """(?<destRangeStart>\d+)\s+(?<sourceRangeStart>\d+)\s+(?<length>\d+)""".toRegex()
    }

    val initialElements: Set<Long>
    val initialElementCategory: String
    var map: Set<AlmanacMapEntry>

    init {
        val rawAlmanacLines = rawAlmanac.split("\n").filter { s -> s.isNotBlank() }
        val firstLineSplit = rawAlmanacLines.first().split(":")
        val elementCategory = firstLineSplit.first().trim()
        this.initialElementCategory = if (elementCategory == "seeds") "seed" else elementCategory
        this.initialElements = firstLineSplit[1].split(" ")
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

    fun getDestinationElement(
        sourceElementCategory: String,
        destinationElementCategory: String,
        sourceElement: Long
    ): Long {
        require(sourceElementCategory in this.map.map { sourceElementCategory }) {
            "Source element category $sourceElementCategory not found"
        }
        require(destinationElementCategory in this.map.map { destinationElementCategory }) {
            "Destination element category $destinationElementCategory not found"
        }
        val foundEntries = this.map
            .filter {
                it.sourceElementCategory == sourceElementCategory
                        && it.destinationElementCategory == destinationElementCategory
            }
            .filter { sourceElement in it.sourceRangeStart..<(it.sourceRangeStart + it.rangeLength) }
            .toSet()
        check(foundEntries.size <= 1) {
            "Found multiple intersecting mapping entries: $foundEntries"
        }
        if (foundEntries.isEmpty()) {
            return sourceElement // Any source numbers that aren't mapped correspond to the same destination number.
        }
        return foundEntries.single().destinationRangeStart + (sourceElement - foundEntries.single().sourceRangeStart)
    }

    fun getDestinationCategory(sourceElementCategory: String): String {
        // assuming there is only one map type for a given source category
        // i.e., there are not CAT1 -> CAT2, CAT1 -> CAT3 mappings
        return this.map
            .filter { it.sourceElementCategory == sourceElementCategory }
            .map { it.destinationElementCategory }
            .distinct().single()
    }

    fun getDestinationElement(
        sourceElementCategory: String,
        sourceElement: Long
    ): Long {
        return getDestinationElement(
            sourceElementCategory,
            getDestinationCategory(sourceElementCategory),
            sourceElement
        )
    }

    data class AlmanacMapEntry(
        val sourceElementCategory: String,
        val destinationElementCategory: String,
        val destinationRangeStart: Long,
        val sourceRangeStart: Long,
        val rangeLength: Long
    )

}
