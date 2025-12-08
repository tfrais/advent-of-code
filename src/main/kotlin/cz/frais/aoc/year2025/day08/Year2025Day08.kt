package cz.frais.aoc.year2025.day08

import cz.frais.aoc.AdventOfCodeDaySolution
import kotlin.collections.mutableMapOf

object Year2025Day08 : AdventOfCodeDaySolution {

    fun parseJunctionBoxes(input: String) = input.lines().map { line ->
        val lineSplit = line.split(",")
        JunctionBox(lineSplit[0].toInt(), lineSplit[1].toInt(), lineSplit[2].toInt())
    }

    fun prepareDistanceMap(junctionBoxes: List<JunctionBox>): MutableMap<Pair<JunctionBox, JunctionBox>, Double> {
        val distanceMap = mutableMapOf<Pair<JunctionBox, JunctionBox>, Double>()
        for (i in 0 until junctionBoxes.size) {
            for (j in i + 1 until junctionBoxes.size) {
                distanceMap[Pair(junctionBoxes[i], junctionBoxes[j])] = junctionBoxes[i].distanceTo(junctionBoxes[j])
            }
        }
        return distanceMap
    }

    override fun computePart1(input: String): Long = computePart1(input, 1000)

    fun computePart1(input: String, numberOfSteps: Int): Long {
        val junctionBoxes = parseJunctionBoxes(input)
        val distanceMap = prepareDistanceMap(junctionBoxes)
        val circuits = junctionBoxes.map { element -> mutableSetOf(element) }.toSet()

        for (distance in distanceMap.toList().sortedBy { (_, value) -> value }.take(numberOfSteps).toMap()) {
            val junctionBoxA = distance.key.first
            val junctionBoxB = distance.key.second

            val junctionBoxACircuit = circuits.single { junctionBoxA in it }
            val junctionBoxBCircuit = circuits.single { junctionBoxB in it }

            if (junctionBoxACircuit != junctionBoxBCircuit) {
                junctionBoxACircuit.addAll(junctionBoxBCircuit)
                junctionBoxBCircuit.clear()
            }

        }

        return circuits.sortedByDescending { it.size }
            .take(3)
            .fold(1L) { acc, circuit -> acc * circuit.size }

    }

    override fun computePart2(input: String): Long {
        val junctionBoxes = parseJunctionBoxes(input)
        val distanceMap = prepareDistanceMap(junctionBoxes)
        val circuits = junctionBoxes.map { element -> mutableSetOf(element) }.toSet()

        var lastJunctionBoxPair: Pair<JunctionBox, JunctionBox> = Pair(junctionBoxes.first(), junctionBoxes.first())

        for (distance in distanceMap.toList().sortedBy { (_, value) -> value }.toMap()) {
            val junctionBoxA = distance.key.first
            val junctionBoxB = distance.key.second

            val junctionBoxACircuit = circuits.single { junctionBoxA in it }
            val junctionBoxBCircuit = circuits.single { junctionBoxB in it }

            if (junctionBoxACircuit != junctionBoxBCircuit) {
                junctionBoxACircuit.addAll(junctionBoxBCircuit)
                junctionBoxBCircuit.clear()
                lastJunctionBoxPair = Pair(junctionBoxA, junctionBoxB)
            }

        }

        return lastJunctionBoxPair.first.x.toLong() * lastJunctionBoxPair.second.x.toLong()
    }

}