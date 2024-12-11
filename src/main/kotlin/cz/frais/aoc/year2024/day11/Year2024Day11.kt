package cz.frais.aoc.year2024.day11

import cz.frais.aoc.AdventOfCodeDaySolution
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph
import java.math.BigInteger

object Year2024Day11 : AdventOfCodeDaySolution {

    private const val NUMBER_OF_BLINKS_PART1 = 25
    private const val NUMBER_OF_BLINKS_PART2 = 75

    @Suppress("MagicNumber")
    private val MULTIPLIER_2024 = BigInteger.valueOf(2024)

    private val cache: MutableMap<Pair<BigInteger, Int>, Long> = mutableMapOf()

    private fun nextValues(value: BigInteger): List<BigInteger> =
        when {
            value == BigInteger.ZERO -> listOf(BigInteger.ONE)

            value.toString().length % 2 == 0 -> {
                val strValue = value.toString()
                listOf(
                    BigInteger(strValue.take(strValue.length / 2)),
                    BigInteger(strValue.drop(strValue.length / 2))
                )
            }

            else -> listOf(value.multiply(MULTIPLIER_2024))
        }

    fun prepareGraph(initialVertexes: List<BigInteger>): Graph<BigInteger, DefaultEdge> {
        val graph = SimpleDirectedGraph<BigInteger, DefaultEdge>(DefaultEdge::class.java)
        initialVertexes.forEach { graph.addVertex(it) }
        while (true) {
            val leaves = graph.vertexSet().filter { graph.outDegreeOf(it) == 0 }
            if (leaves.isEmpty()) break
            leaves.forEach { leaf ->
                nextValues(leaf).forEach { nextValue ->
                    graph.addVertex(nextValue)
                    graph.addEdge(leaf, nextValue)
                }
            }
        }
        return graph
    }

    fun Graph<BigInteger, DefaultEdge>.numberOfPaths(vertex: BigInteger, steps: Int): Long =
        cache.getOrPut(vertex to steps) {
            val outgoingEdges = this.outgoingEdgesOf(vertex)
            if (steps == 1) outgoingEdges.size.toLong() else outgoingEdges
                .map { edge -> this.getEdgeTarget(edge) }
                .sumOf { target -> this.numberOfPaths(target, steps - 1) }
        }

    fun compute(stones: List<BigInteger>, numberOfBlinks: Int): Long {
        val graph = prepareGraph(stones)
        return stones.sumOf { graph.numberOfPaths(it, numberOfBlinks) }.toLong()
    }

    override fun computePart1(input: String): Long {
        return compute(input.split(" ").map { BigInteger(it) }, NUMBER_OF_BLINKS_PART1)
    }

    override fun computePart2(input: String): Long {
        return compute(input.split(" ").map { BigInteger(it) }, NUMBER_OF_BLINKS_PART2)
    }

}
