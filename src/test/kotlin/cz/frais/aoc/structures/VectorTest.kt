package cz.frais.aoc.structures

import cz.frais.aoc.structures.Vector.Companion.withNegativeVectors
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class VectorTest {

    @Test
    fun testVectorApply() {
        assertThat(Vector(1, -1).apply(Position(5, 5)))
            .usingRecursiveComparison().isEqualTo(Position(6, 4))
    }

    @Test
    fun testNegative() {
        assertThat(Vector(1, -1).negative())
            .isEqualTo(Vector(-1, 1))
    }

    @Test
    fun testListWithNegativeVectors() {
        val originalList = listOf(Vector(1, -1), Vector(0, 1))
        assertThat(originalList.withNegativeVectors())
            .isEqualTo(originalList + Vector(-1, 1) + Vector(0, -1))
    }

}
