package cz.frais.aoc.year2023.day02

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2023Day02 : AdventOfCodeDaySolution {

    data class GameTurn(val colorMap: Map<String, Int>)
    data class Game(val id: Int, val turns: List<GameTurn>)

    fun parseGameTurn(rawTurn: String): GameTurn {
        val resultMap: MutableMap<String, Int> = mutableMapOf()
        for (rawColor in rawTurn.split(",").map { it.trim() }) {
            val parts = rawColor.split(" ").map { it.trim() }
            resultMap[parts[1]] = parts[0].toInt()
        }
        return GameTurn(resultMap)
    }

    fun parseGame(rawGame: String): Game {
        val parts = rawGame.split(":")
        val gameId = parts[0].removePrefix("Game ").trim().toInt()
        val gameTurns = parts[1].trim().split(";").map { turn ->
            parseGameTurn(turn.trim())
        }
        return Game(gameId, gameTurns)
    }

    fun getGameMaxMap(game: Game): Map<String, Int> {
        return game.turns.map { it.colorMap }
            .flatMap { it.entries }
            .groupBy({ it.key }, { it.value })
            .mapValues { (_, values) -> values.maxOrNull() ?: 0 }
    }

    fun isGamePossible(game: Game, expectedColors: Map<String, Int>): Boolean {
        return getGameMaxMap(game).all { (key, gameMaxValue) ->
            gameMaxValue <= (expectedColors[key] ?: return@all false)
        }
    }

    fun valueOfPossibleGames(games: List<Game>, expectedColors: Map<String, Int>): Int {
        return games.filter { isGamePossible(it, expectedColors) }
            .sumOf { it.id }
    }

    fun powerOfGame(game: Game): Int {
        return getGameMaxMap(game).values.reduce { acc, number -> acc * number }
    }

    fun powerOfGames(games: List<Game>): Int {
        return games.sumOf { powerOfGame(it) }
    }

    @Suppress("MagicNumber")
    override fun computePart1(input: String): Long {
        return valueOfPossibleGames(
            input.lines().map { parseGame(it) },
            mapOf("red" to 12, "green" to 13, "blue" to 14)
        ).toLong()
    }

    override fun computePart2(input: String): Long {
        return powerOfGames(input.lines().map { parseGame(it) }).toLong()
    }

}
