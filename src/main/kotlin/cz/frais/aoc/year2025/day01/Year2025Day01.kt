package cz.frais.aoc.year2025.day01

import cz.frais.aoc.AdventOfCodeDaySolution
import kotlin.math.abs

object Year2025Day01 : AdventOfCodeDaySolution {

    override fun computePart1(input: String): Long {
        var position = 50;
        var password = 0;
        for (rotation in input.lines().map { Rotation.fromString(it) }) {
            position = rotation.direction.operation(position, rotation.distance)
            if (abs(position) > 99) {
                position = position % 100
            }
            if (position < 0) {
                position = 100 + (position % 100)
            }
            if (position == 0) {
                password += 1
            }
        }

        return password.toLong();
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}