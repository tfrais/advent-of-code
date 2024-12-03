package cz.frais.aoc.structures

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PositionTest {

    @Test
    fun testDistanceToPosition() {
        assertThat(Position(0, 0).distanceTo(Position(0, 0))).isEqualTo(0)
        assertThat(Position(0, 0).distanceTo(Position(1, 0))).isEqualTo(1)
        assertThat(Position(0, 0).distanceTo(Position(0, 3))).isEqualTo(3)
        assertThat(Position(0, 0).distanceTo(Position(3, 0))).isEqualTo(3)
        assertThat(Position(1, 0).distanceTo(Position(5, 5))).isEqualTo(9)
        assertThat(Position(5, 5).distanceTo(Position(1, 0))).isEqualTo(9)
    }

}
