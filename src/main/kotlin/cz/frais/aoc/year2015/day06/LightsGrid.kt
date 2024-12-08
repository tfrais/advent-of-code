package cz.frais.aoc.year2015.day06

interface LightsGrid {

    fun increaseXRangeValues(xRange: IntRange, y: Int)
    fun decreaseXRangeValues(xRange: IntRange, y: Int)
    fun toggleXRangeValues(xRange: IntRange, y: Int)
    fun countLightsOn(): Int

}
