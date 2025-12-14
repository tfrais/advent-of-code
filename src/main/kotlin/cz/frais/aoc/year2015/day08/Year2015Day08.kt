package cz.frais.aoc.year2015.day08

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day08 : AdventOfCodeDaySolution {

    override fun computePart1(input: String): Long {
        var realCharCount = 0
        for (line in input.lines()) {
            var i = 0
            while (i < line.length) {
                if (line[i] == '"') {
                    i++
                } else if (line.substring(i, i + 2) in  listOf("""\\""", """\"""")) {
                    realCharCount++
                    i += 2
                } else if (line.substring(i, i + 2) == """\x""") {
                    realCharCount++
                    i += 4
                } else {
                    realCharCount++
                    i++
                }
            }
        }
        return (input.filter { !it.isWhitespace() }.length - realCharCount).toLong()
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}
