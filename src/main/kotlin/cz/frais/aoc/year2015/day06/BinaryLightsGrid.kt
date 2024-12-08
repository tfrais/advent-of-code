package cz.frais.aoc.year2015.day06

import java.util.*

class BinaryLightsGrid(private val sizeX: Int, sizeY: Int) : LightsGrid {

    private val bitGrid: Array<BitSet> = Array(sizeY) { BitSet(sizeX) }

    private fun setXRangeValues(xRange: IntRange, y: Int, value: Boolean) {
        // beware - BitSet.set(...) has the toIndex exclusive
        bitGrid[y].set(xRange.first, xRange.last + 1, value)
    }

    override fun increaseXRangeValues(xRange: IntRange, y: Int) {
        setXRangeValues(xRange, y, true)
    }

    override fun decreaseXRangeValues(xRange: IntRange, y: Int) {
        setXRangeValues(xRange, y, false)
    }

    private fun toggle(x: Int, y: Int) {
        bitGrid[y][x] = !bitGrid[y][x]
    }

    override fun toggleXRangeValues(xRange: IntRange, y: Int) {
        xRange.forEach { x -> toggle(x, y) }
    }

    override fun countLightsOn(): Int {
        return bitGrid.sumOf { it.cardinality() }
    }

}
