package cz.frais.aoc.year2015.day20

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day20 : AdventOfCodeDaySolution {

    fun divisors(n: Int): Long {
        var result = 0L
        var i = 1L
        while (i * i <= n) {
            if (n % i == 0L) {
                result += i
                if (i != n / i) result += (n / i)
            }
            i++
        }
        return result
    }

    override fun computePart1(input: String): Long {
        val target = input.toLong() / 10
        var n = 1
        var presents = 0L
        while (presents < target) {
            n++
            presents = divisors(n)
        }
        return n.toLong()
    }

    override fun computePart2(input: String): Long {
        val target = input.toInt()
        val limit = target / 11
        val presents = IntArray(limit + 1)

        for (elf in 1..limit) {
            var count = 0
            var house = elf
            while (house <= limit && count < 50) {
                presents[house] += elf * 11
                house += elf
                count++
            }
        }

        for (i in presents.indices) {
            if (presents[i] >= target) return i.toLong()
        }

        error("No solution found")
    }

}
