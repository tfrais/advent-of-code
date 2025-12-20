package cz.frais.aoc.year2015.day16

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day16 : AdventOfCodeDaySolution {

    private val EXPECTED_AUNT = mapOf(
        "children" to 3,
        "cats" to 7,
        "samoyeds" to 2,
        "pomeranians" to 3,
        "akitas" to 0,
        "vizslas" to 0,
        "goldfish" to 5,
        "trees" to 3,
        "cars" to 2,
        "perfumes" to 1
    )

    private fun prepareMap(input: String): List<Map<String, Int>> {
        val aunts = mutableListOf<Map<String, Int>>()
        for (line in input.lines()) {
            aunts.add(
                line.substringAfter(':').trim().split(',').associate({
                    val split = it.split(':')
                    split[0].trim() to split[1].trim().toInt()
                })
            )
        }
        return aunts.toList()
    }

    override fun computePart1(input: String): Long {
        for ((index, aunt) in prepareMap(input).withIndex()) {
            if (aunt.all { (key, value) -> EXPECTED_AUNT[key] == value }) {
                return index + 1L
            }
        }

        throw IllegalStateException("Aunt not found")
    }

    override fun computePart2(input: String): Long {
        for ((index, aunt) in prepareMap(input).withIndex()) {
            if (aunt.all { (key, value) ->
                    when (key) {
                        "cats", "trees" -> EXPECTED_AUNT[key]!! < value
                        "pomeranians", "goldfish" -> EXPECTED_AUNT[key]!! > value
                        else -> EXPECTED_AUNT[key]!! == value
                    }
                }
            ) {
                return index + 1L
            }
        }

        throw IllegalStateException("Aunt not found")
    }

}
