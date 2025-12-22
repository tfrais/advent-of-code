package cz.frais.aoc.year2015.day21

data class Item(
    val cost: Int,
    val damage: Int,
    val armor: Int
)

val WEAPONS = setOf(
    Item(cost = 8, damage = 4, armor = 0),
    Item(cost = 10, damage = 5, armor = 0),
    Item(cost = 25, damage = 6, armor = 0),
    Item(cost = 40, damage = 7, armor = 0),
    Item(cost = 74, damage = 8, armor = 0)
)

val ARMOR = setOf(
    Item(cost = 13, damage = 0, armor = 1),
    Item(cost = 31, damage = 0, armor = 2),
    Item(cost = 53, damage = 0, armor = 3),
    Item(cost = 75, damage = 0, armor = 4),
    Item(cost = 102, damage = 0, armor = 5)
)

val RINGS = setOf(
    Item(cost = 25, damage = 1, armor = 0),
    Item(cost = 50, damage = 2, armor = 0),
    Item(cost = 100, damage = 3, armor = 0),
    Item(cost = 20, damage = 0, armor = 1),
    Item(cost = 40, damage = 0, armor = 2),
    Item(cost = 80, damage = 0, armor = 3)
)
