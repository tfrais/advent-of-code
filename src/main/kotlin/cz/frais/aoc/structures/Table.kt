package cz.frais.aoc.structures

class Table<T>(private val array: List<List<T>>) {

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

    constructor(rawTable: String, charToValueTypeMapper: (Char) -> T) : this(parse(rawTable, charToValueTypeMapper))

    fun height(): Int = array.size
    fun width(): Int = array.first().size

    fun valueAt(position: Position): T {
        require(inTable(position)) {
            "$position not in the table with width ${width()} and height ${height()}"
        }
        return array[position.y][position.x]
    }

    fun findValue(value: T): List<Position> =
        array.indices.flatMap { y ->
            array[y].indices.mapNotNull { x ->
                Position(x, y).takeIf { valueAt(it) == value }
            }
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

    fun positionsFollowingVector(
        initialPosition: Position,
        vector: Vector,
        length: Int,
        shouldFailIfOutsideOfPlan: Boolean = true,
    ): List<Position>? {
        val result = mutableListOf<Position>()
        var currentPosition = initialPosition
        repeat(length) { _ ->
            if (!inTable(currentPosition)) {
                return if (!shouldFailIfOutsideOfPlan) null
                else error("Following vector would lead outside of the table at $currentPosition")
            }
            result.add(currentPosition)
            currentPosition = vector.apply(currentPosition)
        }
        return result
    }

    fun valuesFollowingVector(
        initialPosition: Position,
        vector: Vector,
        length: Int,
        shouldFailIfOutsideOfPlan: Boolean = true,
    ): List<T>? {
        return positionsFollowingVector(
            initialPosition, vector, length, shouldFailIfOutsideOfPlan
        )?.map { valueAt(it) }
    }

    fun withReplacedValues(replacementMap: Map<Position, T>): Table<T> {
        val updatedArray = array.map { it.toList() }.toMutableList()

        replacementMap.forEach { (position, newValue) ->
            updatedArray[position.y] = updatedArray[position.y].toMutableList().apply {
                this[position.x] = newValue
            }
        }

        return Table(updatedArray)
    }

}
