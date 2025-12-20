package cz.frais.aoc.year2015.day14

data class Reindeer(
    val name: String,
    val speed: Int,
    val flyingInterval: Int,
    val restingInterval: Int,
    var distanceTravelled: Int = 0,
    var flyingSeconds: Int = 0,
    var restingSeconds: Int = 0,
    var score: Int = 0
) {

    companion object {

        private val PARSING_REGEX = Regex("""^(\w+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.$""")

        fun fromString(input: String): Reindeer {
            val matchResult = PARSING_REGEX.find(input) ?: throw IllegalArgumentException("Cannot parse: $input")
            return Reindeer(
                name = matchResult.groupValues[1],
                speed = matchResult.groupValues[2].toInt(),
                flyingInterval = matchResult.groupValues[3].toInt(),
                restingInterval = matchResult.groupValues[4].toInt()
            )
        }

    }

    fun fly() {
        if (flyingSeconds < flyingInterval) {
            distanceTravelled += speed
            flyingSeconds++
        } else if (restingSeconds < restingInterval) {
            restingSeconds++
            if (restingSeconds == restingInterval) {
                flyingSeconds = 0
                restingSeconds = 0
            }
        }
    }

}
