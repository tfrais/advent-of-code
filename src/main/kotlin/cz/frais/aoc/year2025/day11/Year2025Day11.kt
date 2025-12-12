package cz.frais.aoc.year2025.day11

import cz.frais.aoc.AdventOfCodeDaySolution
import java.util.LinkedList

object Year2025Day11 : AdventOfCodeDaySolution {

    fun loadMap(input: String): Map<String, Set<String>> {
        val resultMap = mutableMapOf<String, Set<String>>()
        for (line in input.lines()) {
            val split = line.split(":")
            resultMap[split[0]] = split[1].trim().split(Regex("\\s+")).toSet()
        }
        return resultMap.toMap()
    }

    override fun computePart1(input: String): Long {
        val resultMap = loadMap(input)
        val incompletePaths = LinkedList<List<String>>()
        incompletePaths.add(listOf("you"))
        var result = 0L

        while (incompletePaths.isNotEmpty()) {
            val currentPath = incompletePaths.poll()
            if (currentPath.last() == "out") {
                result++
            } else {
                resultMap[currentPath.last()]?.forEach { nextStep ->
                    if (nextStep !in currentPath) {
                        incompletePaths.add(currentPath + nextStep)
                    }
                }
            }
        }
        return result
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}