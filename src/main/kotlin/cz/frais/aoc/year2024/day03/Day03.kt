package cz.frais.aoc.year2024.day03

private val MUL_REGEX: Regex = """mul\((?<a>\d{1,3}),(?<b>\d{1,3})\)""".toRegex()
private const val DO_PHRASE = "do()"
private const val DONT_PHRASE = "don't()"

private val multiplyMatchResult: (MatchResult) -> Int = { match ->
    match.groups["a"]!!.value.toInt() * match.groups["b"]!!.value.toInt()
}

private val PART2_REGEX: Regex =
    """${MUL_REGEX.pattern}|${Regex.escape(DO_PHRASE)}|${Regex.escape(DONT_PHRASE)}""".toRegex()

fun calculatePart1(input: String): Int {
    return MUL_REGEX.findAll(input)
        .map(multiplyMatchResult)
        .sum()
}

fun calculatePart2(input: String): Int {
    var doPhase = true
    var result = 0
    PART2_REGEX.findAll(input).forEach {
        when (it.value) {
            DO_PHRASE -> doPhase = true
            DONT_PHRASE -> doPhase = false
            else -> result += if (doPhase) multiplyMatchResult(it) else 0
        }
    }
    return result
}

fun main() {
    val content = object {}.javaClass.getResource("/2024/day03_input.txt")!!.readText()
    println("Part 1 result is ${calculatePart1(content)}.")
    println("Part 2 result is ${calculatePart2(content)}.")
}
