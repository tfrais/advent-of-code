package cz.frais.aoc.year2024.day11

import cz.frais.aoc.year2024.day11.Year2024Day11.compute
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigInteger

class Year2024Day11Test {

    @Test
    fun testCompute() {
        assertThat(compute(BigInteger.valueOf(125), 6)).isEqualTo(7)
        assertThat(compute(BigInteger.valueOf(17), 6)).isEqualTo(15)
    }

}
