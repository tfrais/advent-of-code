package cz.frais.aoc.year2015

import cz.frais.aoc.year2015.day02.Year2015Day02.computePart1
import cz.frais.aoc.year2015.day02.Year2015Day02.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2015Day02Test {

    @Test
    fun testComputePart1() {
        assertThat(computePart1("2x3x4")).isEqualTo(58)
        assertThat(computePart1("1x1x10")).isEqualTo(43)
    }

    @Test
    fun testComputePart2() {
        assertThat(computePart2("2x3x4")).isEqualTo(34)
        assertThat(computePart2("1x1x10")).isEqualTo(14)
    }

}
