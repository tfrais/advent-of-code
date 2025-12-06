package cz.frais.aoc.year2025.day05

data class KitchenInventory(
    val freshIngredientRanges: List<LongRange>,
    val availableIngredients: List<Long>,
) {

    companion object {

        fun fromString(input: String): KitchenInventory {
            val lines = input.lines()
            val emptyIndex = lines.indexOf("")
            val freshIngredientRangesLines = lines.subList(0, emptyIndex)
            val availableIngredientsLines = lines.subList(emptyIndex + 1, lines.size)

            val freshIngredientRanges = freshIngredientRangesLines.map { line ->
                val split = line.split("-")
                split[0].toLong()..split[1].toLong()
            }

            val availableIngredients = availableIngredientsLines.map { it.toLong() }

            return KitchenInventory(freshIngredientRanges, availableIngredients)
        }

    }

}
