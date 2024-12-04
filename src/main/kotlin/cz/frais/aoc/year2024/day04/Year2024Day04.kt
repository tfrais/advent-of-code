package cz.frais.aoc.year2024.day04

import cz.frais.aoc.AdventOfCodeDaySolution
import cz.frais.aoc.structures.Position
import cz.frais.aoc.structures.Table
import cz.frais.aoc.structures.Vector
import cz.frais.aoc.structures.Vector.Companion.withNegativeVectors

object Year2024Day04 : AdventOfCodeDaySolution {

    private val PART1_VECTORS = listOf(
        Vector(1, 0),
        Vector(0, 1),
        Vector(1, 1),
        Vector(-1, -1),
    ).withNegativeVectors()

    // initial position, vector to move
    private val PART2_VECTOR_MAP = listOf(
        Pair(Vector(-1, -1), Vector(1, 1)),
        Pair(Vector(1, -1), Vector(-1, 1))
    )

    fun countWords(position: Position, word: String, wordTable: Table<Char>): Int {
        return PART1_VECTORS
            .filter { vector ->
                word ==
                        wordTable.valuesFollowingVector(position, vector, word.length, false)
                            ?.joinToString("")
            }
            .size
    }

    fun containsXPattern(position: Position, word: String, wordTable: Table<Char>): Boolean {
        return PART2_VECTOR_MAP.all { (initialPositionVector, moveVector) ->
            wordTable.valuesFollowingVector(
                initialPositionVector.apply(position),
                moveVector,
                word.length,
                true
            )?.joinToString("") in listOf(word, word.reversed())
        }
    }

    fun computePart1(input: String, word: String): Long {
        val wordTable = Table(input) { it }
        return (0 until wordTable.height()).flatMap { y ->
            (0 until wordTable.width()).map { x ->
                countWords(Position(x, y), word, wordTable)
            }
        }.sum().toLong()
    }

    fun computePart2(input: String, word: String): Long {
        val wordTable = Table(input) { it }
        return (1 until wordTable.height() - 1).flatMap { y ->
            (1 until wordTable.width() - 1).map { x ->
                Position(x, y)
            }
        }.count { position ->
            containsXPattern(position, word, wordTable)
        }.toLong()
    }

    override fun computePart1(input: String): Long {
        return computePart1(input, "XMAS")
    }

    override fun computePart2(input: String): Long {
        return computePart2(input, "MAS")
    }

}
