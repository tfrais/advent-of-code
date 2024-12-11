package cz.frais.aoc.year2024.day11

import cz.frais.aoc.year2024.day11.Year2024Day11.blink
import cz.frais.aoc.year2024.day11.Year2024Day11.compute
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigInteger

class Year2024Day11Test {

    @Test
    fun testBlink() {
        assertThat(blink(listOf(BigInteger.valueOf(125), BigInteger.valueOf(17))))
            .containsExactly(
                BigInteger.valueOf(253000), BigInteger.valueOf(1), BigInteger.valueOf(7)
            )
    }

    @Test
    fun testCompute() {
        assertThat(
            compute(
                listOf(BigInteger.valueOf(125), BigInteger.valueOf(17)),
                6
            )
        ).hasSize(22)
    }

}
