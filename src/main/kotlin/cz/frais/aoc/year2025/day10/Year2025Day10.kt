package cz.frais.aoc.year2025.day10

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2025Day10 : AdventOfCodeDaySolution {

    fun applySchematicsPart1(currentLights: List<Boolean>, schematics: List<Int>): List<Boolean> {
        val result = currentLights.toMutableList()
        for (index in schematics) {
            result[index] = !result[index]
        }
        return result.toList()
    }

    fun applySchematicsPart2(currentJoltage: List<Int>, schematics: List<Int>): List<Int> {
        val result = currentJoltage.toMutableList()
        for (index in schematics) {
            result[index] = result[index] + 1
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

    override fun computePart2(input: String): Long {
        val manual = input.lines().map { Machine.fromString(it) }
        var result = 0L

        for (machine in manual) {
            var foundSolution = false
            val paths = mutableListOf<List<List<Int>>>()
            val visited = mutableSetOf<List<Int>>()
            paths.add(mutableListOf(List(machine.joltageRequirements.size) { 0 }))
            var currentSteps = 0
            while (!foundSolution) {
                val currentPath = paths.removeFirst()
                for (schematics in machine.wiring) {
                    val newJoltage = applySchematicsPart2(currentPath.last(), schematics)
                    if (newJoltage == machine.joltageRequirements) {
                        foundSolution = true
                        currentSteps = currentPath.size
                        break
                    }
                    if (newJoltage !in visited) {
                        if (!machine.joltageRequirements.indices.any { i -> newJoltage[i] > machine.joltageRequirements[i] }) {
                            val newPath = currentPath.toMutableList()
                            newPath.add(newJoltage)
                            paths.add(newPath)
                            visited.add(newJoltage)
                        }
                    }
                }
            }
            result += currentSteps
        }

        return result
    }

}