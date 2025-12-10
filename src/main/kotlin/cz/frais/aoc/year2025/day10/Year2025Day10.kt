package cz.frais.aoc.year2025.day10

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2025Day10 : AdventOfCodeDaySolution {

    fun applySchematics(currentLights: List<Boolean>, schematics: List<Int>): List<Boolean> {
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
                    val newLights = applySchematics(currentPath.last(), schematics)
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
        TODO("Not yet implemented")
    }

}