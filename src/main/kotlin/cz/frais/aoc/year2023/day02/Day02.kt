package cz.frais.aoc.year2023.day02

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

data class GameTurn(val colorMap: Map<String, Int>)
data class Game(val id: Int, val turns: List<GameTurn>)

internal fun parseGameTurn(rawTurn: String): GameTurn {
    val resultMap: MutableMap<String, Int> = mutableMapOf()
    for (rawColor in rawTurn.split(",").map { it.trim() }) {
        val parts = rawColor.split(" ").map { it.trim() }
        resultMap[parts[1]] = parts[0].toInt()
    }
    return GameTurn(resultMap)
}

internal fun parseGame(rawGame: String): Game {
    val parts = rawGame.split(":")
    val gameId = parts[0].removePrefix("Game ").trim().toInt()
    val gameTurns = parts[1].trim().split(";").map { turn ->
        parseGameTurn(turn.trim())
    }
    return Game(gameId, gameTurns)
}

internal fun getGameMaxMap(game: Game): Map<String, Int> {
    return game.turns.map { it.colorMap }
        .flatMap { it.entries }
        .groupBy({ it.key }, { it.value })
        .mapValues { (_, values) -> values.maxOrNull() ?: 0 }
}

internal fun isGamePossible(game: Game, expectedColors: Map<String, Int>): Boolean {
    return getGameMaxMap(game).all { (key, gameMaxValue) ->
        gameMaxValue <= (expectedColors[key] ?: return@all false)
    }
}

internal fun valueOfPossibleGames(games: List<Game>, expectedColors: Map<String, Int>): Int {
    return games.filter { isGamePossible(it, expectedColors) }
        .sumOf { it.id }
}

internal fun powerOfGame(game: Game): Int {
    return getGameMaxMap(game).values.reduce { acc, number -> acc * number }
}

internal fun powerOfGames(games: List<Game>): Int {
    return games.sumOf { powerOfGame(it) }
}

@Suppress("MagicNumber")
fun main() {
    val content = object {}.javaClass.getResource("/2023/day02_input.txt")!!.readText()
    val parsedGames = content.lines().map { parseGame(it) }
    val expectedColors = mapOf("red" to 12, "green" to 13, "blue" to 14)

    logger.info { "Part 1 result is ${valueOfPossibleGames(parsedGames, expectedColors)}." }
    logger.info { "Part 2 result is ${powerOfGames(parsedGames)}." }
}
