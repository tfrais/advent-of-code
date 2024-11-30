package cz.frais.aoc.year2023.day07

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day07Test {

    private val parser = Parser()

    @Test
    fun testStrengthOfCard() {
        assertThat(Card.THREE.strength()).isGreaterThan(Card.TWO.strength())
        assertThat(Card.ACE.strength()).isGreaterThan(Card.TWO.strength())
    }

    @Test
    fun testParseHand() {
        assertThat(parser.parseHand("T55J5 684")).usingRecursiveComparison().isEqualTo(
            Hand(
                listOf(Card.TEN, Card.FIVE, Card.FIVE, Card.JACK, Card.FIVE),
                684
            )
        )
    }

    @Test
    fun testResolveHandType() {
        assertThat(Type.resolveType(parser.parseHand("23456 0"))).isEqualTo(Type.HIGH_CARD)
        assertThat(Type.resolveType(parser.parseHand("A23A4 0"))).isEqualTo(Type.ONE_PAIR)
        assertThat(Type.resolveType(parser.parseHand("23432 0"))).isEqualTo(Type.TWO_PAIR)
        assertThat(Type.resolveType(parser.parseHand("TTT98 0"))).isEqualTo(Type.THREE_OF_KIND)
        assertThat(Type.resolveType(parser.parseHand("23332 0"))).isEqualTo(Type.FULL_HOUSE)
        assertThat(Type.resolveType(parser.parseHand("AA8AA 0"))).isEqualTo(Type.FOUR_OF_KIND)
        assertThat(Type.resolveType(parser.parseHand("AAAAA 0"))).isEqualTo(Type.FIVE_OF_KIND)
    }

    @Test
    fun testCalculatePart1ForInputFile() {
        val input = object {}.javaClass.getResource("/2023/day07_test_input.txt")!!.readText()
        val actual = calculatePart1(input.lines().map { parser.parseHand(it) })
        assertThat(actual).isEqualTo(6440)
    }

}
