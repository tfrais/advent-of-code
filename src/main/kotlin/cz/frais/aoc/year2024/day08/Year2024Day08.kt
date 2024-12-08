package cz.frais.aoc.year2024.day08

import cz.frais.aoc.AdventOfCodeDaySolution
import cz.frais.aoc.structures.Position
import cz.frais.aoc.structures.Table
import cz.frais.aoc.structures.Vector

object Year2024Day08 : AdventOfCodeDaySolution {

    fun antinodePositions(antenna1: Position, antenna2: Position): Set<Position> {
        val diffVector = Vector(antenna2.x - antenna1.x, antenna2.y - antenna1.y)
        return setOf(
            diffVector.negative().apply(antenna1),
            diffVector.apply(diffVector.apply(antenna1))
        )
    }

    fun buildAntennaFrequencyPositionMap(table: Table<Char>): Map<Char, Set<Position>> {
        val antennaFrequencyPositionMap = mutableMapOf<Char, MutableSet<Position>>()
        for (y in 0 until table.height()) {
            for (x in 0 until table.width()) {
                val position = Position(x, y)
                val valueAtPosition = table.valueAt(position)
                if (valueAtPosition != '.') {
                    antennaFrequencyPositionMap.getOrPut(valueAtPosition) { mutableSetOf() }.add(position)
                }
            }
        }
        return antennaFrequencyPositionMap.mapValues { (_, value) -> value.toSet() }
    }

    fun <T> buildPairs(fromSet: Set<T>): Set<Pair<T, T>> {
        return fromSet.flatMap { a ->
            fromSet.filter { b -> a != b && a.hashCode() <= b.hashCode() }
                .map { b -> Pair(a, b) }
        }.toSet()
    }

    override fun computePart1(input: String): Long {
        val table = Table(input) { it }
        val allAntinodePositions = mutableSetOf<Position>()
        val antennaFrequencyPositionMap = buildAntennaFrequencyPositionMap(table)
        antennaFrequencyPositionMap.values.forEach { frequencyPositions ->
            allAntinodePositions.addAll(
                buildPairs(frequencyPositions)
                    .flatMap { antinodePositions(it.first, it.second) }
                    .filter { table.inTable(it) }
            )
        }
        return allAntinodePositions.size.toLong()
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}
