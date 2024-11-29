package cz.frais.aoc.year2023.day02

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

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

fun isGamePossible(game: Game, expectedColors: Map<String, Int>): Boolean {
    val gameMaxMap = game.turns.map { it.colorMap }
        .flatMap { it.entries }
        .groupBy({ it.key }, { it.value })
        .mapValues { (_, values) -> values.maxOrNull() ?: 0 }

    return gameMaxMap.all { (key, gameMaxValue) ->
        gameMaxValue <= (expectedColors[key] ?: return@all false)
    }
}

fun valueOfPossibleGames(games: List<Game>, expectedColors: Map<String, Int>): Int {
    return games.filter { isGamePossible(it, expectedColors) }
        .sumOf { it.id }
}

@Suppress("MagicNumber")
fun main() {
    val content = object {}.javaClass.getResource("/2023/day02_input.txt")!!.readText()
    val expectedColors = mapOf("red" to 12, "green" to 13, "blue" to 14)
    val result = valueOfPossibleGames(content.lines().map { parseGame(it) }, expectedColors)
    logger.info { "Result is $result." }
}
