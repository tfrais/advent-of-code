package cz.frais.aoc.year2025.day07

import cz.frais.aoc.AdventOfCodeDaySolution
import cz.frais.aoc.structures.Position
import cz.frais.aoc.structures.Table
import java.util.LinkedList

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

    private data class Timeline(
        val beamsXPositions: MutableSet<Int>,
        val yPosition: Int
    )

    override fun computePart2(input: String): Long {
        val table: Table<DiagramElement> = Table(input) { DiagramElement.fromChar(it) }
        val startPosition = table.findValue(DiagramElement.START).single()

        var finishedTimelinesCount = 0L
        val timelinesInProgress = LinkedList<Timeline>()
        timelinesInProgress.add(Timeline(mutableSetOf(startPosition.x), 1))

        while (timelinesInProgress.isNotEmpty()) {
            val currentTimeline = timelinesInProgress.removeLast()
            if (currentTimeline.yPosition == table.height()) {
                finishedTimelinesCount++;
            } else {
                var splitHappened = false
                for (beamX in currentTimeline.beamsXPositions) {
                    if (table.valueAt(Position(beamX, currentTimeline.yPosition)) == DiagramElement.SPLITTER) {
                        splitHappened = true
                        if (beamX > 0) {
                            timelinesInProgress += Timeline(
                                (currentTimeline.beamsXPositions - beamX + (beamX - 1)) as MutableSet<Int>,
                                currentTimeline.yPosition
                            )
                        }
                        if (beamX < table.width() - 1) {
                            timelinesInProgress += Timeline(
                                (currentTimeline.beamsXPositions - beamX + (beamX + 1)) as MutableSet<Int>,
                                currentTimeline.yPosition
                            )
                        }
                    }
                }
                if (!splitHappened) {
                    timelinesInProgress += Timeline(currentTimeline.beamsXPositions, currentTimeline.yPosition + 1)
                }
            }
        }

        return finishedTimelinesCount;
    }

}