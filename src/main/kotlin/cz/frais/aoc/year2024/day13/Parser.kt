package cz.frais.aoc.year2024.day13

import cz.frais.aoc.structures.Vector

object Parser {

    private const val PARSING_CHUNK_SIZE = 4

    private val BUTTON_REGEX = Regex(
        """^Button\s+(?<button>\w+):\s+X\+(?<vectorX>\d+),\s+Y\+(?<vectorY>\d+)$"""
    )
    private val PRIZE_REGEX = Regex(
        """^Prize:\s+X=(?<priceX>\d+),\s+Y=(?<priceY>\d+)$"""
    )

    fun parse(input: String): List<Machine> =
        input.lines()
            .chunked(PARSING_CHUNK_SIZE)
            .map { lines ->
                val buttonMatches = lines.flatMap { BUTTON_REGEX.findAll(it) }
                val prizeMatch = lines.asSequence().flatMap { PRIZE_REGEX.findAll(it) }.single()

                Machine(
                    buttonMatches.associate { match ->
                        val button = Button.fromCode(match.groups["button"]!!.value)
                        val vectorX = match.groups["vectorX"]!!.value.toInt()
                        val vectorY = match.groups["vectorY"]!!.value.toInt()
                        button to Vector(vectorX, vectorY)
                    },
                    prizeMatch.groups["priceX"]!!.value.toLong(),
                    prizeMatch.groups["priceY"]!!.value.toLong()
                )
            }

}
