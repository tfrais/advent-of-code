package cz.frais.aoc.year2024.day08

import cz.frais.aoc.AdventOfCodeDaySolution
import cz.frais.aoc.structures.Position
import cz.frais.aoc.structures.Table
import cz.frais.aoc.structures.Vector

object Year2024Day08 : AdventOfCodeDaySolution {

    fun antinodePositionsForPart1(antenna1: Position, antenna2: Position): Set<Position> {
        val diffVector = Vector(antenna2.x - antenna1.x, antenna2.y - antenna1.y)
        return setOf(
            diffVector.negative().apply(antenna1),
            diffVector.apply(diffVector.apply(antenna1))
        )
    }

    fun positionsFollowingVector(
        initialPosition: Position,
        vector: Vector,
        table: Table<Char>,
    ): Set<Position> {
        val resultSet = mutableSetOf<Position>()
        var currentPosition = initialPosition
        while (table.inTable(currentPosition)) {
            resultSet.add(currentPosition)
            currentPosition = vector.apply(currentPosition)
        }
        return resultSet.toSet()
    }

    fun antinodePositionsForPart2(
        antenna1: Position,
        antenna2: Position,
        table: Table<Char>,
    ): Set<Position> {
        val resultSet = mutableSetOf<Position>()
        resultSet.addAll(setOf(antenna1, antenna2))
        val diffVector = Vector(antenna2.x - antenna1.x, antenna2.y - antenna1.y)
        setOf(diffVector, diffVector.negative())
            .forEach { vector ->
                resultSet.addAll(positionsFollowingVector(antenna1, vector, table))
            }
        return resultSet.toSet()
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

    fun compute(
        input: String,
        antinodePositionsFunction: (Table<Char>, Pair<Position, Position>) -> Set<Position>,
    ): Long {
        val table = Table(input) { it }
        val allAntinodePositions = mutableSetOf<Position>()
        buildAntennaFrequencyPositionMap(table).values.forEach { frequencyPositions ->
            allAntinodePositions.addAll(
                buildPairs(frequencyPositions)
                    .flatMap { antinodePositionsFunction(table, it) }
                    .filter { table.inTable(it) }
            )
        }
        return allAntinodePositions.size.toLong()
    }

    override fun computePart1(input: String): Long {
        return compute(input) { _, pair ->
            antinodePositionsForPart1(pair.first, pair.second)
        }
    }

    override fun computePart2(input: String): Long {
        return compute(input) { table, pair ->
            antinodePositionsForPart2(pair.first, pair.second, table)
        }
    }

}
