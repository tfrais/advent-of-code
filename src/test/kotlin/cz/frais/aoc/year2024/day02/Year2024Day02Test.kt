package cz.frais.aoc.year2024.day02

import cz.frais.aoc.year2024.day02.Year2024Day02.isSafe
import cz.frais.aoc.year2024.day02.Year2024Day02.isSafeAfterRemovingElement
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Year2024Day02Test {

    @Test
    fun testIsSafe() {
        assertTrue(isSafe(listOf(7, 6, 4, 2, 1)))
        assertFalse(isSafe(listOf(1, 2, 7, 8, 9)))
        assertFalse(isSafe(listOf(9, 7, 6, 2, 1)))
        assertFalse(isSafe(listOf(1, 3, 2, 4, 5)))
        assertFalse(isSafe(listOf(8, 6, 4, 4, 1)))
        assertTrue(isSafe(listOf(1, 3, 6, 7, 9)))
    }

    @Test
    fun testIsSafeAfterRemovingElement() {
        assertTrue(isSafeAfterRemovingElement(listOf(7, 6, 4, 2, 1)))
        assertFalse(isSafeAfterRemovingElement(listOf(1, 2, 7, 8, 9)))
        assertFalse(isSafeAfterRemovingElement(listOf(9, 7, 6, 2, 1)))
        assertTrue(isSafeAfterRemovingElement(listOf(1, 3, 2, 4, 5)))
        assertTrue(isSafeAfterRemovingElement(listOf(8, 6, 4, 4, 1)))
        assertTrue(isSafeAfterRemovingElement(listOf(1, 3, 6, 7, 9)))
    }

}
