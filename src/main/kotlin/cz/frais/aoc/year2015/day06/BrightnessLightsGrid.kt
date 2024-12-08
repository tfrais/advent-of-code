package cz.frais.aoc.year2015.day06

class BrightnessLightsGrid(private val sizeX: Int, sizeY: Int) : LightsGrid {

    private val array: Array<Array<Int>> = Array(sizeY) { Array(sizeX) { 0 } }

    override fun increaseXRangeValues(xRange: IntRange, y: Int) {
        for (x in xRange) {
            array[y][x] = array[y][x] + 1
        }
    }

    override fun decreaseXRangeValues(xRange: IntRange, y: Int) {
        for (x in xRange) {
            array[y][x] = if (array[y][x] > 0) array[y][x] - 1 else 0
        }
    }

    override fun toggleXRangeValues(xRange: IntRange, y: Int) {
        for (x in xRange) {
            array[y][x] = array[y][x] + 2
        }
    }

    override fun countLightsOn(): Int {
        return array.sumOf { xArray -> xArray.sum() }
    }

}
