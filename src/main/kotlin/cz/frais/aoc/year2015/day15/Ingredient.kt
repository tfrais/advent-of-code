package cz.frais.aoc.year2015.day15

data class Ingredient(
    val name: String,
    val capacity: Int,
    val durability: Int,
    val flavor: Int,
    val texture: Int,
    val calories: Int
) {

    companion object {

        private val PARSING_REGEX =
            Regex("""^(\w+): capacity (-?\d+), durability (-?\d+), flavor (-?\d+), texture (-?\d+), calories (-?\d+)$""")

        fun fromString(input: String): Ingredient {
            val matchResult = PARSING_REGEX.find(input) ?: throw IllegalArgumentException("Cannot parse: $input")
            return Ingredient(
                name = matchResult.groupValues[1],
                capacity = matchResult.groupValues[2].toInt(),
                durability = matchResult.groupValues[3].toInt(),
                flavor = matchResult.groupValues[4].toInt(),
                texture = matchResult.groupValues[5].toInt(),
                calories = matchResult.groupValues[6].toInt()
            )
        }

    }

}
