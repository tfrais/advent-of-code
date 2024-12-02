package cz.frais.aoc.year2023.day10

internal object PipeCalculator {

    fun nextPositions(currentPosition: Position, symbol: Symbol): List<Position> =
        symbol.moveVectors.map { it.apply(currentPosition) }

    private fun positionsLeadingTo(plan: Plan, destinationPosition: Position): List<Position> {
        return plan.adjacentPositions(destinationPosition)
            .filter { destinationPosition in nextPositions(it, plan.symbol(it)) }
    }

    fun pipe(plan: Plan): List<Position> {
        val pipe = mutableListOf<Position>()
        pipe.add(plan.startPosition())
        var nextPosition = positionsLeadingTo(plan, pipe.last()).first()
        while (plan.symbol(nextPosition) != Symbol.START) {
            pipe.add(nextPosition)
            nextPosition = nextPositions(nextPosition, plan.symbol(nextPosition))
                .first { it != pipe.dropLast(1).lastOrNull() }
        }
        return pipe.toList()
    }

}
