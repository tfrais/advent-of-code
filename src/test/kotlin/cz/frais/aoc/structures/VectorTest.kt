package cz.frais.aoc.structures

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class VectorTest {

    @Test
    fun testVectorApply() {
        assertThat(Vector(1, -1).apply(Position(5, 5)))
            .usingRecursiveComparison().isEqualTo(Position(6, 4))
    }

}
