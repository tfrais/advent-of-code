package cz.frais.aoc.year2025.day05

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2025Day05 : AdventOfCodeDaySolution {

    override fun computePart1(input: String): Long {
        val kitchenInventory = KitchenInventory.fromString(input)
        var result = 0L
        for (ingredient in kitchenInventory.availableIngredients) {
            if (kitchenInventory.freshIngredientRanges.any { range -> ingredient in range }) {
                result++
            }
        }
        return result
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}