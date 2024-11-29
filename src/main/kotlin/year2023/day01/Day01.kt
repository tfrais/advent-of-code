package year2023.day01

fun calibrationValue(line: String): Int {
    val digits = Regex("""\d""").findAll(line).map { matchResult -> matchResult.value.toInt() }
    return 10 * digits.first() + digits.last()
}

fun calibrationValue(lines: List<String>): Int {
    var result = 0
    lines.forEach { line -> result += calibrationValue(line) }
    return result
}

fun main() {
    val content = object {}.javaClass.getResource("/2023/day01_input.txt")!!.readText()
    val result = calibrationValue(content.lines())
    println(result)
}