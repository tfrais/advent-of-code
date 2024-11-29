package cz.frais.aoc.year2023.day02

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day02Test {

    @Test
    fun testParseGameTurn() {
        val rawTurn = " 1 red, 2 green, 6 blue "
        val expected = GameTurn(
            mapOf(
                "red" to 1,
                "green" to 2,
                "blue" to 6
            )
        )
        assertThat(parseGameTurn(rawTurn)).usingRecursiveComparison().isEqualTo(expected)
    }

    @Test
    fun testParseGame() {
        val rawGame = "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"
        val expected = Game(
            2,
            listOf(
                GameTurn(mapOf("blue" to 1, "green" to 2)),
                GameTurn(mapOf("green" to 3, "blue" to 4, "red" to 1)),
                GameTurn(mapOf("green" to 1, "blue" to 1)),
            )
        )
        assertThat(parseGame(rawGame)).usingRecursiveComparison().isEqualTo(expected)
    }

    @Test
    fun testIsGamePossibleTrue() {
        val rawGame = "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"
        val expectedColors = mapOf("green" to 3, "blue" to 5, "red" to 1)
        assertThat(isGamePossible(parseGame(rawGame), expectedColors)).isTrue()
    }

    @Test
    fun testIsGamePossibleFalseMissingColor() {
        val rawGame = "Game 2: 1 blue, 1 red"
        val expectedColors = mapOf("blue" to 2)
        assertThat(isGamePossible(parseGame(rawGame), expectedColors)).isFalse()
    }

    @Test
    fun testIsGamePossibleFalseNotEnoughGems() {
        val rawGame = "Game 2: 5 blue, 2 green; 3 green, 4 blue; 1 green, 1 blue"
        val expectedColors = mapOf("blue" to 4, "green" to 4)
        assertThat(isGamePossible(parseGame(rawGame), expectedColors)).isFalse()
    }

    @Test
    fun testValueOfPossibleGamesForInputFile() {
        val content = object {}.javaClass.getResource("/2023/day02_test_input_part1.txt")!!.readText()
        val expectedColors = mapOf("red" to 12, "green" to 13, "blue" to 14)
        val actual = valueOfPossibleGames(content.lines().map { parseGame(it) }, expectedColors)
        assertThat(actual).isEqualTo(8)
    }
}
