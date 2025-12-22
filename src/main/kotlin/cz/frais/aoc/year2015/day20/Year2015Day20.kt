package cz.frais.aoc.year2015.day20

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day20 : AdventOfCodeDaySolution {

    fun divisors(n: Int): Long {
        var result = 0L
        var i = 1L
        while (i * i <= n) {
            if (n % i == 0L) {
                result += i * 10
                if (i != n / i) result += (n / i) * 10
            }
            i++
        }
        return result
    }

    override fun computePart1(input: String): Long {
        val target = input.toLong()
        var n = 1
        var presents = 0L
        while (presents < target) {
            n++
            presents = divisors(n)
        }
        return n.toLong()
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}
