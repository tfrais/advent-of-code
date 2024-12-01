package cz.frais.aoc.year2023.day09

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day09Test {

    @Test
    fun testHistory() {
        assertThat(history("1 3 6 10 15 21"))
            .usingRecursiveComparison().isEqualTo(
                listOf(
                    listOf(1, 3, 6, 10, 15, 21),
                    listOf(2, 3, 4, 5, 6),
                    listOf(1, 1, 1, 1),
                    listOf(0, 0, 0)
                )
            )
    }

    @Test
    fun testExtrapolate() {
        assertThat(extrapolate("0 3 6 9 12 15")).isEqualTo(18)
        assertThat(extrapolate("1 3 6 10 15 21")).isEqualTo(28)
        assertThat(extrapolate("10 13 16 21 30 45")).isEqualTo(68)

    }

}
