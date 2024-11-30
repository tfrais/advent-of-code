package cz.frais.aoc.year2023.day05

internal data class Almanac(
    val categoryMap: Map<String, List<AlmanacMapEntry>>,
    val categoryOrder: List<String>
) {

    data class AlmanacMapEntry(
        val destinationRangeStart: Long,
        val sourceRangeStart: Long,
        val rangeLength: Long
    )

    fun getElement(element: Long, elementCategory: String): Long {
        for (entry in categoryMap[elementCategory]!!) {
            if (element in entry.sourceRangeStart..<entry.sourceRangeStart+entry.rangeLength) {
                return entry.destinationRangeStart + (element - entry.sourceRangeStart)
            }
        }
        // Any source numbers that aren't mapped correspond to the same destination number.
        return element
    }

    fun getFinalElement(element: Long): Long {
        var currentElement = element
        for (category in categoryOrder.dropLast(1)) {
            currentElement = getElement(currentElement, category)
        }
        return currentElement
    }

}
