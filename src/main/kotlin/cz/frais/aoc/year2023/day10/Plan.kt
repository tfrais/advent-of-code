package cz.frais.aoc.year2023.day10

internal class Plan(rawPlan: String) {

    companion object {

        private fun parse(rawPlan: String): List<List<Symbol>> {
            return rawPlan.split("\n")
                .map { line -> line.toCharArray().map { Symbol.fromChar(it) } }
        }

    }

    private val array: List<List<Symbol>> = parse(rawPlan)

    fun symbol(position: Position): Symbol {
        return array[position.y][position.x]
    }

    private fun inPlan(position: Position): Boolean {
        return position.x in array.first().indices
                && position.y in array.indices
    }

    fun adjacentPositions(position: Position): List<Position> =
        (-1..1).flatMap { diffX ->
            (-1..1).mapNotNull { diffY ->
                if (diffX != 0 || diffY != 0) {
                    MoveVector(diffX, diffY)
                        .apply(position)
                        .takeIf { inPlan(it) }
                } else null
            }
        }

    fun startPosition(): Position {
        for (y in array.indices) {
            for (x in array[y].indices) {
                if (array[y][x] == Symbol.START) {
                    return Position(x, y)
                }
            }
        }
        error("Could not locate start position")
    }

}
