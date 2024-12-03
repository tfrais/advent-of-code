package cz.frais.aoc.year2023.day11

import cz.frais.aoc.Position

data class Space(
    val galaxies: List<Position>,
    val width: Int,
    val height: Int,
) {

    fun expanded(expansionSize: Int = 2): Space {
        var expandedGalaxies = this.galaxies
        var newWidth = this.width
        var newHeight = this.height

        var x = 0
        while (x < newWidth) {
            if (expandedGalaxies.none { it.x == x }) {
                expandedGalaxies = expandedGalaxies.map {
                    if (it.x > x) Position(it.x + (expansionSize - 1), it.y) else it
                }
                newWidth += (expansionSize - 1)
                x += (expansionSize - 1)
            }
            x++
        }

        var y = 0
        while (y < newHeight) {
            if (expandedGalaxies.none { it.y == y }) {
                expandedGalaxies = expandedGalaxies.map {
                    if (it.y > y) Position(it.x, it.y + (expansionSize - 1)) else it
                }
                newHeight += (expansionSize - 1)
                y += (expansionSize - 1)
            }
            y++
        }

        return Space(expandedGalaxies, newWidth, newHeight)
    }

}
