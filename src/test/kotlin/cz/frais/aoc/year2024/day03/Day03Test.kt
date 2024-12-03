package cz.frais.aoc.year2024.day03

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day03Test {

    @Test
    fun testCalculatePart1() {
        assertThat(
            calculatePart1(
                "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
            )
        ).isEqualTo(161)
    }

}
