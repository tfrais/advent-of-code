package cz.frais.aoc.year2024.day03

private val MUL_REGEX: Regex =
    """mul\((?<a>\d{1,3}),(?<b>\d{1,3})\)""".toRegex()

internal fun calculatePart1(input: String): Int {
    return MUL_REGEX.findAll(input)
        .map { it.groups["a"]!!.value.toInt() * it.groups["b"]!!.value.toInt() }
        .sum()
}

fun main() {
    val content = object {}.javaClass.getResource("/2024/day03_input.txt")!!.readText()
    println("Part 1 result is ${calculatePart1(content)}.")
}
