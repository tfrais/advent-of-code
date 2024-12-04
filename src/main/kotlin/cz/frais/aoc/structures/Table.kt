package cz.frais.aoc.structures

class Table<T>(rawTable: String, charToValueTypeMapper: (Char) -> T) {

    companion object {

        fun <T> parse(rawPlan: String, charToValueTypeMapper: (Char) -> T): List<List<T>> {
            val lines = rawPlan.split("\n")
            require(lines.map { it.length }.distinct().size == 1) {
                "Inconsistent width of the table"
            }
            return lines.map { line ->
                line.map { char -> charToValueTypeMapper(char) }
            }
        }

    }

    private val array: List<List<T>> = parse(rawTable, charToValueTypeMapper)

    fun height(): Int = array.size
    fun width(): Int = array.first().size

    fun valueAt(position: Position): T {
        require(inTable(position)) {
            "$position not in the table with width ${width()} and height ${height()}"
        }
        return array[position.y][position.x]
    }

    fun inTable(position: Position): Boolean {
        return position.x in array.first().indices
                && position.y in array.indices
    }

    fun adjacentPositions(position: Position): Set<Position> =
        (-1..1).flatMap { diffX ->
            (-1..1).mapNotNull { diffY ->
                if (diffX == 0 && diffY == 0) null
                else {
                    Vector(diffX, diffY)
                        .apply(position)
                        .takeIf { inTable(it) }
                }
            }
        }.toSet()

}
