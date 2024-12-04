package cz.frais.aoc.year2015

import cz.frais.aoc.year2015.day03.Year2015Day03.computePart1
import cz.frais.aoc.year2015.day03.Year2015Day03.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2015Day03Test {

    @Test
    fun testComputePart1() {
        assertThat(computePart1(">")).isEqualTo(2)
        assertThat(computePart1("^>v<")).isEqualTo(4)
        assertThat(computePart1("^v^v^v^v^v")).isEqualTo(2)
    }

    @Test
    fun testComputePart2() {
        assertThat(computePart2("^v")).isEqualTo(3)
        assertThat(computePart2("^>v<")).isEqualTo(3)
        assertThat(computePart2("^v^v^v^v^v")).isEqualTo(11)
    }

}
