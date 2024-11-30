package cz.frais.aoc.year2023.day06

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day06Test {

    @Test
    fun testNumberOfWays() {
        assertThat(numberOfWays(Race(7L, 9L))).isEqualTo(4L)
        assertThat(numberOfWays(Race(15L, 40L))).isEqualTo(8L)
        assertThat(numberOfWays(Race(30L, 200L))).isEqualTo(9L)
    }

}
