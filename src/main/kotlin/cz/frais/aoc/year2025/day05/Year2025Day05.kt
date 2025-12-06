package cz.frais.aoc.year2025.day05

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2025Day05 : AdventOfCodeDaySolution {

    fun haveOverlap(rangeA: LongRange, rangeB: LongRange): Boolean =
        rangeA.first <= rangeB.last && rangeB.first <= rangeA.last

    fun LongRange.merge(other: LongRange): LongRange =
        minOf(this.first, other.first)..maxOf(this.last, other.last)

    fun mergeRanges(ranges: List<LongRange>): List<LongRange> {
        var mergedRanges = ranges.toMutableList()
        do {
            var changed = false
            mergeLoop@ for (i in mergedRanges.indices) {
                for (j in i + 1 until mergedRanges.size) {
                    if (haveOverlap(mergedRanges[i], mergedRanges[j])) {
                        changed = true

                        val newRange = mergedRanges[i].merge(mergedRanges[j])
                        mergedRanges.removeAt(j)
                        mergedRanges.removeAt(i)
                        mergedRanges.add(newRange)

                        break@mergeLoop
                    }
                }
            }
        } while (changed)
        return mergedRanges
    }

    override fun computePart1(input: String): Long {
        val kitchenInventory = KitchenInventory.fromString(input)
        var result = 0L
        for (ingredient in kitchenInventory.availableIngredients) {
            if (kitchenInventory.freshIngredientRanges.any { range -> ingredient in range }) {
                result++
            }
        }
        return result
    }

    override fun computePart2(input: String): Long {
        return mergeRanges(KitchenInventory.fromString(input).freshIngredientRanges)
            .sumOf { range -> range.last - range.first + 1 }
    }

}