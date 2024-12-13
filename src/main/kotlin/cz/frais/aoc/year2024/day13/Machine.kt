package cz.frais.aoc.year2024.day13

import cz.frais.aoc.structures.Vector

data class Machine(
    val buttonVectors: Map<Button, Vector>,
    val priceX: Long,
    val priceY: Long,
)
