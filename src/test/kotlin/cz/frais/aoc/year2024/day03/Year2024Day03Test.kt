package cz.frais.aoc.year2024.day03

import cz.frais.aoc.year2024.day03.Year2024Day03.computePart1
import cz.frais.aoc.year2024.day03.Year2024Day03.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2024Day03Test {

    @Test
    fun testComputePart1() {
        assertThat(
            computePart1(
                "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
            )
        ).isEqualTo(161)
    }

    @Test
    fun testComputePart2() {
        assertThat(
            computePart2(
                "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
            )
        ).isEqualTo(48)
    }

}
