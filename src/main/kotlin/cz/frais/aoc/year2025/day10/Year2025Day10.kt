package cz.frais.aoc.year2025.day10

import cz.frais.aoc.AdventOfCodeDaySolution
import org.ojalgo.optimisation.ExpressionsBasedModel

object Year2025Day10 : AdventOfCodeDaySolution {

    fun applySchematicsPart1(currentLights: List<Boolean>, schematics: List<Int>): List<Boolean> {
        val result = currentLights.toMutableList()
        for (index in schematics) {
            result[index] = !result[index]
        }
        return result.toList()
    }

    override fun computePart1(input: String): Long {
        val manual = input.lines().map { Machine.fromString(it) }
        var result = 0L

        for (machine in manual) {
            var foundSolution = false
            val paths = mutableListOf<List<List<Boolean>>>()
            val visited = mutableSetOf<List<Boolean>>()
            paths.add(mutableListOf(List(machine.diagram.size) { false }))
            var currentSteps = 0
            while (!foundSolution) {
                val currentPath = paths.removeFirst()
                for (schematics in machine.wiring) {
                    val newLights = applySchematicsPart1(currentPath.last(), schematics)
                    if (newLights == machine.diagram) {
                        foundSolution = true
                        currentSteps = currentPath.size
                        break
                    }
                    if (newLights !in visited) {
                        val newPath = currentPath.toMutableList()
                        newPath.add(newLights)
                        paths.add(newPath)
                        visited.add(newLights)
                    }
                }
            }
            result += currentSteps
        }

        return result
    }

    fun solveByIntegerLinearProgramming(
        vectors: List<IntArray>,
        target: IntArray
    ): Long {

        val numDimensions = target.size
        val model = ExpressionsBasedModel()

        val xVars = vectors.mapIndexed { i, _ ->
            model.addVariable("x_$i")
                .integer(true)
                .lower(0)
                .weight(1.0)
        }

        // Add equality constraints for each counter/dimension
        for (d in 0 until numDimensions) {
            val expr = model.addExpression("dim_$d").level(target[d].toDouble())
            for (i in vectors.indices) {
                expr.set(xVars[i], vectors[i][d].toDouble())
            }
        }

        val result = model.minimise()

        if (!result.state.isOptimal) {
            throw IllegalStateException("No feasible solution found: ${result.state}")
        }

        return xVars.sumOf { v -> result.get(model.indexOf(v).toLong()).toLong() }
    }

    override fun computePart2(input: String): Long {
        val manual = input.lines().map { Machine.fromString(it) }
        var result = 0L

        for (machine in manual) {
            val dimension = machine.joltageRequirements.size
            result += solveByIntegerLinearProgramming(
                vectors = machine.wiring.map { schematics -> IntArray(dimension) { if (it in schematics) 1 else 0 } },
                target = machine.joltageRequirements.toIntArray()
            )
        }

        return result
    }

}