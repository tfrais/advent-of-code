package cz.frais.aoc.year2024.day09

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2024Day09 : AdventOfCodeDaySolution {

    private const val FREE_SPACE_VALUE = -1
    private const val MAX_EXPANSION_RATIO = 9

    fun loadIntoExpandedArray(input: String): Array<Int> {
        val array = IntArray(input.length * MAX_EXPANSION_RATIO)
        var fileId = 0
        var arrayIndex = 0
        input.forEachIndexed { inputIndex, char ->
            val valueToFill = if (inputIndex % 2 == 0) fileId++ else FREE_SPACE_VALUE
            repeat(char.digitToInt()) {
                array[arrayIndex++] = valueToFill
            }
        }
        return array.take(arrayIndex).toTypedArray()
    }

    private fun <T> Array<T>.nextIndex(fromIndex: Int, valuePredicate: (T) -> Boolean): Int =
        (fromIndex until this.size).firstOrNull { valuePredicate(this[it]) } ?: -1

    private fun <T> Array<T>.previousIndex(fromIndex: Int, valuePredicate: (T) -> Boolean): Int =
        (fromIndex downTo 0).firstOrNull { valuePredicate(this[it]) } ?: -1

    override fun computePart1(input: String): Long {
        val array = loadIntoExpandedArray(input)

        var freeSpaceIndex = array.indexOfFirst { it == FREE_SPACE_VALUE }
        var rightBlockIndex = array.indexOfLast { it != FREE_SPACE_VALUE }

        while (freeSpaceIndex < rightBlockIndex) {
            array[freeSpaceIndex] = array[rightBlockIndex]
            array[rightBlockIndex] = FREE_SPACE_VALUE
            freeSpaceIndex = array.nextIndex(freeSpaceIndex) { it == FREE_SPACE_VALUE }
            rightBlockIndex = array.previousIndex(rightBlockIndex) { it != FREE_SPACE_VALUE }
        }

        return array.take(freeSpaceIndex).withIndex()
            .sumOf { (index, value) -> index * value.toLong() }
    }

    override fun computePart2(input: String): Long {
        val array = loadIntoExpandedArray(input)
        var blockEndIndex = array.indexOfLast { it != FREE_SPACE_VALUE }

        while (blockEndIndex >= 1) {
            val blockStartIndex = array.previousIndex(blockEndIndex) { it != array[blockEndIndex] } + 1
            val blockSize = blockEndIndex - blockStartIndex + 1

            var freeSpaceStartIndex = array.indexOfFirst { it == FREE_SPACE_VALUE }
            var moved = false
            while (freeSpaceStartIndex < blockStartIndex && !moved) {
                val freeSpaceEndIndex = array.nextIndex(freeSpaceStartIndex) { it != FREE_SPACE_VALUE } - 1
                val freeSpaceSize = freeSpaceEndIndex - freeSpaceStartIndex + 1

                if (freeSpaceSize >= blockSize) {
                    for (i in 0 until blockSize) {
                        val fileId = array[blockEndIndex]
                        array[freeSpaceStartIndex + i] = fileId
                        array[blockStartIndex + i] = FREE_SPACE_VALUE
                    }
                    moved = true
                }

                freeSpaceStartIndex += freeSpaceSize + 1

            }

            blockEndIndex -= blockSize
        }

        return array.withIndex()
            .sumOf { (index, value) -> index * (if (value > 0) value.toLong() else 0) }
    }

}
