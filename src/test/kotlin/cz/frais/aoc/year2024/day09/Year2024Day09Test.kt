package cz.frais.aoc.year2024.day09

import cz.frais.aoc.year2024.day09.Year2024Day09.computePart1
import cz.frais.aoc.year2024.day09.Year2024Day09.computePart2
import cz.frais.aoc.year2024.day09.Year2024Day09.loadIntoExpandedArray
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2024Day09Test {

    @Test
    fun testLoadIntoExpandedArray() {
        assertThat(loadIntoExpandedArray("12345")).containsExactly(
            0, -1, -1, 1, 1, 1, -1, -1, -1, -1, 2, 2, 2, 2, 2
        )
    }

    @Test
    fun testComputePart1() {
        assertThat(computePart1("123")).isEqualTo(6)
        assertThat(computePart1("1234")).isEqualTo(6)
        assertThat(computePart1("12345")).isEqualTo(60)
        assertThat(computePart1("2333133121414131402")).isEqualTo(1928)
    }

    @Test
    fun testComputePart2() {
        assertThat(computePart2("2333133121414131402")).isEqualTo(2858)
    }

}
