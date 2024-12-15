package cz.frais.aoc.year2024.day15

import cz.frais.aoc.structures.Position
import cz.frais.aoc.year2024.day15.Year2024Day15.computePart1
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2024Day15Test {

    @Test
    fun testMove() {
        val sokoban = Parser.parse("#@.\n\n>")
        assertThat(sokoban.currentPosition).isEqualTo(Position(1, 0))
        sokoban.move(Direction.WEST)
        assertThat(sokoban.currentPosition).isEqualTo(Position(1, 0))
        sokoban.move(Direction.NORTH)
        assertThat(sokoban.currentPosition).isEqualTo(Position(1, 0))
        sokoban.move(Direction.EAST)
        assertThat(sokoban.currentPosition).isEqualTo(Position(2, 0))
    }

    @Test
    fun testMoveAndPushBoxes() {
        val sokoban = Parser.parse("#OO@OO.\n\n>")
        assertThat(sokoban.currentPosition).isEqualTo(Position(3, 0))
        sokoban.move(Direction.EAST)
        assertThat(sokoban.currentPosition).isEqualTo(Position(4, 0))
        assertThat(sokoban.map.valueAt(Position(4, 0))).isEqualTo(MapElement.EMPTY)
        assertThat(sokoban.map.findValue(MapElement.BOX)).containsExactly(
            Position(1, 0), Position(2, 0), Position(5, 0), Position(6, 0)
        )
    }

    @Test
    fun testMoveAndPushBoxesAgainstWall() {
        val sokoban = Parser.parse("#OO@OO.\n\n>")
        assertThat(sokoban.currentPosition).isEqualTo(Position(3, 0))
        sokoban.move(Direction.WEST)
        assertThat(sokoban.currentPosition).isEqualTo(Position(3, 0))
        assertThat(sokoban.map.findValue(MapElement.BOX)).containsExactly(
            Position(1, 0), Position(2, 0), Position(4, 0), Position(5, 0)
        )
        assertThat(sokoban.currentPosition).isEqualTo(Position(3, 0))
    }

    @Test
    fun testComputePart1() {
        val input = object {}.javaClass.getResource("/2024/day15_test_input.txt")!!.readText()
        assertThat(computePart1(input)).isEqualTo(10092)
    }

}
