package cz.frais.aoc.year2025.day08

import kotlin.math.pow
import kotlin.math.sqrt

data class JunctionBox(
    val x: Int,
    val y: Int,
    val z: Int
) {

    fun distanceTo(other: JunctionBox): Double =
        sqrt(
            (this.x - other.x).toDouble().pow(2) +
                    (this.y - other.y).toDouble().pow(2) +
                    (this.z - other.z).toDouble().pow(2)
        )

}