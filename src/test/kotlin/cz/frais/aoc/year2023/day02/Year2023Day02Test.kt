package cz.frais.aoc.year2023.day02

import cz.frais.aoc.year2023.day02.Year2023Day02.isGamePossible
import cz.frais.aoc.year2023.day02.Year2023Day02.parseGame
import cz.frais.aoc.year2023.day02.Year2023Day02.parseGameTurn
import cz.frais.aoc.year2023.day02.Year2023Day02.powerOfGame
import cz.frais.aoc.year2023.day02.Year2023Day02.powerOfGames
import cz.frais.aoc.year2023.day02.Year2023Day02.valueOfPossibleGames
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2023Day02Test {

    @Test
    fun testParseGameTurn() {
        val rawTurn = " 1 red, 2 green, 6 blue "
        val expected = Year2023Day02.GameTurn(
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
        val expected = Year2023Day02.Game(
            2,
            listOf(
                Year2023Day02.GameTurn(mapOf("blue" to 1, "green" to 2)),
                Year2023Day02.GameTurn(mapOf("green" to 3, "blue" to 4, "red" to 1)),
                Year2023Day02.GameTurn(mapOf("green" to 1, "blue" to 1)),
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
        val content = object {}.javaClass.getResource("/2023/day02_test_input.txt")!!.readText()
        val expectedColors = mapOf("red" to 12, "green" to 13, "blue" to 14)
        val actual = valueOfPossibleGames(content.lines().map { parseGame(it) }, expectedColors)
        assertThat(actual).isEqualTo(8)
    }

    @Test
    fun testPowerOfGame() {
        val rawGame = "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"
        assertThat(powerOfGame(parseGame(rawGame))).isEqualTo(1560)
    }

    @Test
    fun testPowerOfGamesForInputFile() {
        val content = object {}.javaClass.getResource("/2023/day02_test_input.txt")!!.readText()
        val actual = powerOfGames(content.lines().map { parseGame(it) })
        assertThat(actual).isEqualTo(2286)
    }
}
