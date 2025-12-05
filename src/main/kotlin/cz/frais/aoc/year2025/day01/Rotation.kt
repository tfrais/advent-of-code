package cz.frais.aoc.year2025.day01

data class Rotation(
    val direction: Direction,
    val distance: Int,
) {

    enum class Direction(val operation: Int.(Int) -> Int) {
        L(operation = Int::minus),
        R(operation = Int::plus)
    }

    companion object {

        fun fromString(rotationString: String): Rotation {
            return Rotation(
                Direction.valueOf(rotationString.first().uppercaseChar().toString()),
                rotationString.substring(1).toInt()
            )
        }

    }

}