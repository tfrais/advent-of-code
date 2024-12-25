package cz.frais.aoc.year2024.day24

data class System(
    val wireValues: MutableMap<String, Boolean>,
    val remainingGates: MutableList<Gate>,
) {

    fun solveNextGate() {
        val nextGate = remainingGates.first() { gate ->
            gate.input1Name in wireValues.keys && gate.input2Name in wireValues
        }
        wireValues[nextGate.outputName] = nextGate.operation.function(
            wireValues[nextGate.input1Name]!!,
            wireValues[nextGate.input2Name]!!
        )
        remainingGates.remove(nextGate)
    }

    fun finalOutput() =
        wireValues.filterKeys { key -> key.startsWith("z", ignoreCase = true) }
            .asSequence()
            .map { (k, v) -> k to if (v) 1 else 0 }
            .sortedBy { it.first }
            .map { it.second }
            .mapIndexed { index, bit -> bit.toLong() * (1L shl index) }
            .sum()

}
