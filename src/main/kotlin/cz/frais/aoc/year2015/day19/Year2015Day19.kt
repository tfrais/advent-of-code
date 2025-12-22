package cz.frais.aoc.year2015.day19

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day19 : AdventOfCodeDaySolution {

    private data class ParsingResult(val transitions: Set<Pair<String, String>>, val string: String)

    private fun parseInput(input: String) = ParsingResult(
        transitions = input.lines().dropLast(2).map { it.split(" => ") }.map { it[0] to it[1] }.toSet(),
        string = input.lines().last()
    )

    override fun computePart1(input: String): Long {
        val parsedInput = parseInput(input)
        val molecules = mutableSetOf<String>()

        for (i in parsedInput.string.indices) {
            for (entry in parsedInput.transitions) {
                if (i + entry.first.length - 1 < parsedInput.string.length && parsedInput.string.substring(i until i + entry.first.length) == entry.first) {
                    molecules.add(parsedInput.string.take(i) + entry.second + parsedInput.string.substring(i + entry.first.length))
                }
            }
        }

        return molecules.size.toLong()
    }

    fun newWords(original: String, replacement: Pair<String, String>): Set<String> {
        val result = mutableSetOf<String>()
        var index = original.indexOf(replacement.first)

        while (index >= 0) {
            val newText =
                original.take(index) + replacement.second + original.substring(index + replacement.first.length)
            result.add(newText)
            index = original.indexOf(replacement.first, index + 1)
        }

        return result
    }

    override fun computePart2(input: String): Long {
        val parsedInput = parseInput(input)
        val distanceMap = mutableMapOf("e" to 0)
        val queue = ArrayDeque<String>()
        queue += "e"
        while (parsedInput.string !in distanceMap) {
            val current = queue.removeFirst()
            val currentDistance = distanceMap[current]!!
            for (transition in parsedInput.transitions) {
                for (newWord in newWords(current, transition)) {
                    if (newWord.length > parsedInput.string.length) continue
                    if (newWord.length >= 13 && newWord.dropLast(13) != parsedInput.string.take(newWord.length - 13)) continue
                    if (newWord in distanceMap) continue

                    distanceMap[newWord] = currentDistance + 1
                    queue += newWord
                }
            }
        }

        return distanceMap[parsedInput.string]!!.toLong()
    }

}
