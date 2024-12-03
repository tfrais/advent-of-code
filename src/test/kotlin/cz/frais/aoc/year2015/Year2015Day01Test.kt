package cz.frais.aoc.year2015

import cz.frais.aoc.year2015.day01.Year2015Day01.computePart1
import cz.frais.aoc.year2015.day01.Year2015Day01.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2015Day01Test {

    @Test
    fun testComputePart1() {
        assertThat(computePart1("(())")).isEqualTo(0)
        assertThat(computePart1("))(((((")).isEqualTo(3)
        assertThat(computePart1(")())())")).isEqualTo(-3)
    }

    @Test
    fun testComputePart2() {
        assertThat(computePart2(")")).isEqualTo(1)
        assertThat(computePart2("()())")).isEqualTo(5)
    }

}
