package cz.frais.aoc.year2015.day06

import java.util.*

class LightsGrid(private val sizeX: Int, sizeY: Int) {

    private val bitGrid: Array<BitSet> = Array(sizeY) { BitSet(sizeX) }

    fun get(x: Int, y: Int): Boolean = bitGrid[y][x]

    fun setXRangeValues(xRange: IntRange, y: Int, value: Boolean) {
        // beware - BitSet.set(...) has the toIndex exclusive
        bitGrid[y].set(xRange.first, xRange.last + 1, value)
    }

    fun toggle(x: Int, y: Int) {
        bitGrid[y][x] = !bitGrid[y][x]
    }

    fun toggle(xRange: IntRange, y: Int) {
        xRange.forEach { x -> toggle(x, y) }
    }

    fun countLightsOn(): Int {
        return bitGrid.sumOf { it.cardinality() }
    }

}
