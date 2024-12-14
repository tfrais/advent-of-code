package cz.frais.aoc.year2024.day14

import cz.frais.aoc.structures.Position
import cz.frais.aoc.year2024.day14.Year2024Day14.compute
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2024Day14Test {

    @Test
    fun testRobotMove() {
        val robot = Robot("p=2,4 v=2,-3")
        val areaWidth = 11
        val areaHeight = 7
        robot.move(areaWidth, areaHeight, 1)
        assertThat(robot.currentPosition).isEqualTo(Position(4, 1))
        robot.move(areaWidth, areaHeight, 1)
        assertThat(robot.currentPosition).isEqualTo(Position(6, 5))
        robot.move(areaWidth, areaHeight, 1)
        assertThat(robot.currentPosition).isEqualTo(Position(8, 2))
        robot.move(areaWidth, areaHeight, 1)
        assertThat(robot.currentPosition).isEqualTo(Position(10, 6))
        robot.move(areaWidth, areaHeight, 1)
        assertThat(robot.currentPosition).isEqualTo(Position(1, 3))
    }

    @Test
    fun testCompute() {
        val input = object {}.javaClass.getResource("/2024/day14_test_input.txt")!!.readText()
        val robots = input.lines().map { Robot(it) }
        assertThat(compute(robots, 11, 7, 100)).isEqualTo(12)
    }

}
