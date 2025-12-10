package cz.frais.aoc.year2025.day09

import cz.frais.aoc.AdventOfCodeDaySolution
import kotlin.math.abs

object Year2025Day09 : AdventOfCodeDaySolution {

    override fun computePart1(input: String): Long {
        val corners = input.lines().map { line ->
            val lineSplit = line.split(",")
            Pair(lineSplit[0].toLong(), lineSplit[1].toLong())
        }

        var maxArea = 0L
        for (i in 0 until corners.size) {
            for (j in i + 1 until corners.size) {
                val area = abs(corners[i].first - corners[j].first + 1) * abs(corners[i].second - corners[j].second + 1)
                if (area > maxArea) {
                    maxArea = area
                }
            }
        }

        return maxArea
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}