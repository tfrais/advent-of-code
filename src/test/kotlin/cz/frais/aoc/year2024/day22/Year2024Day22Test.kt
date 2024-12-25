package cz.frais.aoc.year2024.day22

import cz.frais.aoc.year2024.day22.Year2024Day22.computePart2
import cz.frais.aoc.year2024.day22.Year2024Day22.findChangesPriceInPart2Sequence
import cz.frais.aoc.year2024.day22.Year2024Day22.generatePart2Sequence
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

    @Test
    fun testGeneratePart2Sequence() {
        assertThat(generatePart2Sequence(123, 5)).containsExactly(
            Year2024Day22.Part2SequenceElement(3, Int.MIN_VALUE),
            Year2024Day22.Part2SequenceElement(0, -3),
            Year2024Day22.Part2SequenceElement(6, 6),
            Year2024Day22.Part2SequenceElement(5, -1),
            Year2024Day22.Part2SequenceElement(4, -1),
            Year2024Day22.Part2SequenceElement(4, 0)
        )
    }

    @Test
    fun testFindChangesPriceInPart2Sequence() {
        val sequence = generatePart2Sequence(123, 10)
        assertThat(findChangesPriceInPart2Sequence(listOf(-1, -1, 0, 2), sequence)).isEqualTo(6)
        assertThat(findChangesPriceInPart2Sequence(listOf(1, 2, 3, 4), sequence)).isNull()
    }

    @Test
    fun testComputePart2() {
        assertThat(computePart2("1\n2\n3\n2024")).isEqualTo(23)
    }

}
