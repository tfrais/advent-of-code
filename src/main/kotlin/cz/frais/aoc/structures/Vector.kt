package cz.frais.aoc.structures

data class Vector(val diffX: Int, val diffY: Int) {

    fun apply(position: Position): Position {
        return Position(
            position.x + diffX,
            position.y + diffY
        )
    }

    fun negative(): Vector {
        return Vector(-1 * this.diffX, -1 * this.diffY)
    }

    companion object {

        fun Collection<Vector>.withNegativeVectors(): Collection<Vector> {
            return this + this.map { it.negative() }
        }
    }

}
