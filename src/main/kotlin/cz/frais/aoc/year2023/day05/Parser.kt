package cz.frais.aoc.year2023.day05

class Parser {

    companion object {
        private val MAP_HEADER_REGEX: Regex = """(?<source>\w+)-to-(?<destination>\w+)\smap:""".toRegex()
        private val MAP_ITEM_REGEX: Regex =
            """(?<destRangeStart>\d+)\s+(?<sourceRangeStart>\d+)\s+(?<length>\d+)""".toRegex()
    }

    fun parse(input: String, areInitialElementsRanges: Boolean): ParserResult {
        val rawLines = input.split("\n").filter { it.isNotBlank() }
        val firstLineSplit = rawLines.first().split(":")
        val rawElementCategory = firstLineSplit.first().trim()
        val initialElementCategory = if (rawElementCategory == "seeds") "seed" else rawElementCategory
        val initialElementRanges = parseInitialElementRanges(firstLineSplit[1].trim(), areInitialElementsRanges)
        val almanac = parseAlmanac(rawLines.drop(1), initialElementCategory)

        return ParserResult(initialElementCategory, initialElementRanges, almanac)
    }

    private fun parseInitialElementRanges(
        rawInitialElementRanges: String,
        initialElementsAsRanges: Boolean
    ): List<LongRange> {
        val splitInitialElementRanges = rawInitialElementRanges.split(" ")
            .filter { it.isNotBlank() }
            .map { it.trim().toLong() }

        return if (initialElementsAsRanges) {
            splitInitialElementRanges
                .chunked(2)
                .map { (rangeStart, rangeLength) ->
                    LongRange(rangeStart, rangeStart + rangeLength - 1)
                }
                .toList()
        } else {
            splitInitialElementRanges
                .map { LongRange(it, it) }
                .toList()
        }
    }

    private fun parseAlmanac(rawAlmanacLines: List<String>, initialElementCategory: String): Almanac {
        val categoryMap = mutableMapOf<String, List<Almanac.AlmanacMapEntry>>()
        val categoryOrderMap = mutableMapOf<String, String>()

        var sourceElementCategory = ""
        var destinationElementCategory = ""
        for (line in rawAlmanacLines) {
            val mapHeaderMatchResult = MAP_HEADER_REGEX.find(line)
            if (mapHeaderMatchResult != null) {
                sourceElementCategory = mapHeaderMatchResult.groups["source"]!!.value
                destinationElementCategory = mapHeaderMatchResult.groups["destination"]!!.value
                categoryOrderMap[sourceElementCategory] = destinationElementCategory
            } else {
                check(sourceElementCategory.isNotBlank() && destinationElementCategory.isNotBlank()) {
                    "Map header could not be found or parsed"
                }
                val mapItemMatchResult = MAP_ITEM_REGEX.find(line)
                check(mapItemMatchResult != null) {
                    "Could not parse this map item line: $line"
                }
                if (!categoryMap.contains(sourceElementCategory)) {
                    categoryMap[sourceElementCategory] = mutableListOf()
                }
                (categoryMap[sourceElementCategory] as MutableList).add(
                    Almanac.AlmanacMapEntry(
                        mapItemMatchResult.groups["destRangeStart"]!!.value.toLong(),
                        mapItemMatchResult.groups["sourceRangeStart"]!!.value.toLong(),
                        mapItemMatchResult.groups["length"]!!.value.toLong()
                    )
                )
            }
        }

        return Almanac(
            categoryMap.toMap(),
            prepareCategoryOrder(categoryOrderMap, initialElementCategory)
        )
    }

    private fun prepareCategoryOrder(
        categoryOrderMap: Map<String, String>,
        initialElementCategory: String
    ): List<String> {
        val resultList = mutableListOf(initialElementCategory)
        while (resultList.size <= categoryOrderMap.keys.size) {
            resultList.add(categoryOrderMap[resultList.last()]!!)
        }
        return resultList.toList()
    }

}
