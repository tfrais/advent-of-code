package cz.frais.aoc.year2015

import cz.frais.aoc.year2015.day10.Year2015Day10.expandPart1
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2015Day10Test {

    @Test
    fun testExpandPart1() {
//        assertThat(expandPart1("1")).isEqualTo("11")
        assertThat(expandPart1("11")).isEqualTo("21")
        assertThat(expandPart1("21")).isEqualTo("1211")
        assertThat(expandPart1("1211")).isEqualTo("111221")
        assertThat(expandPart1("111221")).isEqualTo("312211")
    }

}
