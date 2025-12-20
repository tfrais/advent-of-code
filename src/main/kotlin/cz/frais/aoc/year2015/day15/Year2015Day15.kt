package cz.frais.aoc.year2015.day15

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day15 : AdventOfCodeDaySolution {

    private fun score(ingredients: List<Ingredient>, cookie: List<Int>): Long {
        require(ingredients.size == cookie.size)

        var capacity = 0L
        var durability = 0L
        var flavor = 0L
        var texture = 0L

        for (i in ingredients.indices) {
            capacity += ingredients[i].capacity * cookie[i]
            durability += ingredients[i].durability * cookie[i]
            flavor += ingredients[i].flavor * cookie[i]
            texture += ingredients[i].texture * cookie[i]
        }

        return (if (capacity > 0) capacity else 0) * (if (durability > 0) durability else 0) * (if (flavor > 0) flavor else 0) * (if (texture > 0) texture else 0)
    }

    override fun computePart1(input: String): Long {
        val ingredients = input.lines().map { Ingredient.fromString(it) }

        var maxScore = 0L
        val inProgress = mutableSetOf(List(ingredients.size) { 0 })
        while (inProgress.isNotEmpty()) {
            val current = inProgress.first()
            inProgress.remove(current)
            if (current.sum() < 100) {
                for (i in ingredients.indices) {
                    inProgress.add(current.mapIndexed { index, value -> if (index == i) value + 1 else value })
                }
            } else {
                val score = score(ingredients, current)
                if (score > maxScore) {
                    maxScore = score
                }
            }
        }

        return maxScore
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}
