package cz.frais.aoc.year2015.day04

import cz.frais.aoc.AdventOfCodeDaySolution
import java.security.MessageDigest

@Suppress("unused")
object Year2015Day04 : AdventOfCodeDaySolution {

    // https://www.baeldung.com/kotlin/md5-hash
    @OptIn(ExperimentalStdlibApi::class)
    private fun computeMD5(input: String): String {
        return MessageDigest.getInstance("MD5")
            .digest(input.toByteArray())
            .toHexString()
    }

    private fun compute(input: String, numberOfPrefixZeros: Int): Long {
        return generateSequence(0L) { it + 1 }
            .first { i ->
                computeMD5(input + i.toString())
                    .startsWith("0".repeat(numberOfPrefixZeros))
            }
    }

    @Suppress("MagicNumber")
    override fun computePart1(input: String): Long {
        return compute(input, 5)
    }

    @Suppress("MagicNumber")
    override fun computePart2(input: String): Long {
        return compute(input, 6)
    }

}
