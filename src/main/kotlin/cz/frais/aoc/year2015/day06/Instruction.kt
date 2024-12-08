package cz.frais.aoc.year2015.day06

data class Instruction(
    val type: InstructionType,
    val xRange: IntRange,
    val yRange: IntRange,
) {

    fun applyToLightsGrid(lightsGrid: LightsGrid) {
        for (y in yRange) {
            when (type) {
                InstructionType.TURN_ON -> lightsGrid.increaseXRangeValues(xRange, y)
                InstructionType.TURN_OFF -> lightsGrid.decreaseXRangeValues(xRange, y)
                InstructionType.TOGGLE -> lightsGrid.toggleXRangeValues(xRange, y)
            }
        }
    }

}
