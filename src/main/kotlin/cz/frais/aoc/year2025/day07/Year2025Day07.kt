package cz.frais.aoc.year2025.day07

import cz.frais.aoc.AdventOfCodeDaySolution
import cz.frais.aoc.structures.Position
import cz.frais.aoc.structures.Table

object Year2025Day07 : AdventOfCodeDaySolution {

    override fun computePart1(input: String): Long {
        val table: Table<DiagramElement> = Table(input) { DiagramElement.fromChar(it) }
        val startPosition = table.findValue(DiagramElement.START).single()
        var beamsXPositions = mutableSetOf(startPosition.x)
        var splitCount = 0L

        for (y in 1 until table.height()) {
            val newBeamsXPositions = beamsXPositions.toMutableSet()
            for (beamX in beamsXPositions) {
                if (table.valueAt(Position(beamX, y)) == DiagramElement.SPLITTER) {
                    splitCount++
                    newBeamsXPositions -= beamX
                    if (beamX > 0) {
                        newBeamsXPositions += beamX - 1
                    }
                    if (beamX < table.width() - 1) {
                        newBeamsXPositions += beamX + 1
                    }
                }
            }
            beamsXPositions = newBeamsXPositions
        }
        return splitCount
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}