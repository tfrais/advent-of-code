package cz.frais.aoc.structures

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertNull

class TableTest {

    companion object {
        private val SIMPLE_TABLE = Table("ABC\nDEF") { it }
    }

    @Test
    fun testValueAt() {
        assertThat(SIMPLE_TABLE.valueAt(Position(1, 0))).isEqualTo('B')
        assertThat(SIMPLE_TABLE.valueAt(Position(0, 1))).isEqualTo('D')
    }

    @Test
    fun testValueAtSuccessForIntTable() {
        assertThat(
            Table("1") { it.digitToInt() }
                .valueAt(Position(0, 0))
        ).isEqualTo(1)
    }


    @Test
    fun testValueAtThrowExceptionForPositionOutsideTheTable() {
        assertThrows<IllegalArgumentException> { SIMPLE_TABLE.valueAt(Position(-1, 0)) }
        assertThrows<IllegalArgumentException> { SIMPLE_TABLE.valueAt(Position(0, 10)) }

    }

    @Test
    fun testHeight() {
        assertThat(SIMPLE_TABLE.height()).isEqualTo(2)
    }

    @Test
    fun testWidth() {
        assertThat(SIMPLE_TABLE.width()).isEqualTo(3)
    }

    @Test
    fun testInTable() {
        assertTrue(SIMPLE_TABLE.inTable(Position(0, 0)))
        assertFalse(SIMPLE_TABLE.inTable(Position(-1, 0)))
        assertTrue(SIMPLE_TABLE.inTable(Position(0, SIMPLE_TABLE.height() - 1)))
        assertFalse(SIMPLE_TABLE.inTable(Position(0, SIMPLE_TABLE.height())))
        assertTrue(SIMPLE_TABLE.inTable(Position(SIMPLE_TABLE.width() - 1, 0)))
        assertFalse(SIMPLE_TABLE.inTable(Position(SIMPLE_TABLE.width(), 0)))
    }

    @Test
    fun testAdjacentPositions() {
        assertThat(
            Table("A", { it })
                .adjacentPositions(Position(0, 0))
        ).isEmpty()

        assertThat(
            Table("...\n...\n...", { it })
                .adjacentPositions(Position(1, 1))
                .size
        ).isEqualTo(8)

        assertThat(SIMPLE_TABLE.adjacentPositions(Position(0, 0)))
            .containsExactlyInAnyOrder(
                Position(1, 0),
                Position(0, 1),
                Position(1, 1)
            )
    }

    @Test
    fun testPositionsFollowingVector() {
        assertThat(SIMPLE_TABLE.positionsFollowingVector(Position(0, 0), Vector(1, 0), 3))
            .containsExactly(
                Position(0, 0),
                Position(1, 0),
                Position(2, 0)
            )

        assertThat(SIMPLE_TABLE.positionsFollowingVector(Position(0, 0), Vector(1, 1), 2))
            .containsExactly(
                Position(0, 0),
                Position(1, 1)
            )
    }

    @Test
    fun testPositionsFollowingVectorOutsideTable() {
        assertThrows<IllegalStateException> {
            SIMPLE_TABLE
                .positionsFollowingVector(
                    Position(0, 0),
                    Vector(1, 0),
                    10,
                    true
                )
        }

        assertNull(
            SIMPLE_TABLE
                .positionsFollowingVector(
                    Position(0, 0),
                    Vector(1, 0),
                    10,
                    false
                )
        )
    }

    @Test
    fun testValuesFollowingVectorOutsideTable() {
        assertThat(SIMPLE_TABLE.valuesFollowingVector(Position(0, 0), Vector(1, 0), 3))
            .containsExactly('A', 'B', 'C')
    }

}
