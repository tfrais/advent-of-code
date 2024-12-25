package cz.frais.aoc.year2024.day22

import cz.frais.aoc.year2024.day22.Year2024Day22.nextSecretNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2024Day22Test {

    @Test
    fun testNextSecretNumber() {
        assertThat(nextSecretNumber(123)).isEqualTo(15887950)
        assertThat(nextSecretNumber(15887950)).isEqualTo(16495136)
    }

    @Test
    fun testNextSecretNumberRepeated() {
        assertThat(nextSecretNumber(1, 2000)).isEqualTo(8685429)
        assertThat(nextSecretNumber(10, 2000)).isEqualTo(4700978)
        assertThat(nextSecretNumber(100, 2000)).isEqualTo(15273692)
        assertThat(nextSecretNumber(2024, 2000)).isEqualTo(8667524)
    }

}
