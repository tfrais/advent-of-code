package cz.frais.aoc

data class Position(val x: Int, val y: Int) {

    fun distanceTo(destinationPosition: Position): Int {
        return (destinationPosition.x - this.x) + (destinationPosition.y - this.y)
    }

}
