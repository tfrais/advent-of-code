package cz.frais.aoc.year2015.day21

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day21 : AdventOfCodeDaySolution {

    fun playerWins(
        playerMaxHp: Int, playerDamage: Int, playerArmor: Int,
        bossMaxHp: Int, bossDamage: Int, bossArmor: Int
    ): Boolean {
        var playerHp = playerMaxHp
        var bossHp = bossMaxHp

        while (true) {
            bossHp -= maxOf(playerDamage - bossArmor, 1)
            if (bossHp <= 0) return true

            playerHp -= maxOf(bossDamage - playerArmor, 1)
            if (playerHp <= 0) return false
        }
    }

    fun <T> combineSets(setA: Set<Set<T>>, setB: Set<T>): Set<Set<T>> {
        val result = mutableSetOf<Set<T>>()
        for (a in setA) {
            for (b in setB) {
                result += (a + b)
            }
        }
        return result.toSet()
    }

    fun <T> combinationsOfSize(input: List<T>, size: Int): Set<Set<T>> {
        if (size == 0) return setOf(emptySet())
        if (size > input.size) return emptySet()

        val result = mutableSetOf<Set<T>>()
        for (i in input.indices) {
            val head = input[i]
            val tail = input.drop(i + 1)
            for (comb in combinationsOfSize(tail, size - 1)) {
                result.add(setOf(head) + comb)
            }
        }
        return result
    }

    fun <T> combinationsInRange(set: Set<T>, range: IntRange): Set<Set<T>> {
        val allCombinations = mutableSetOf<Set<T>>()
        for (size in range) {
            allCombinations.addAll(combinationsOfSize(set.toList(), size))
        }
        return allCombinations
    }

    override fun computePart1(input: String): Long {
        val inputLines = input.lines()
        val bossMaxHp = inputLines[0].split(":")[1].trim().toInt()
        val bossDamage = inputLines[1].split(":")[1].trim().toInt()
        val bossArmor = inputLines[2].split(":")[1].trim().toInt()

        val ringsAndWeapons = combineSets(combinationsInRange(RINGS, 0..2), WEAPONS)
        val itemCombinations = ringsAndWeapons + combineSets(ringsAndWeapons, ARMOR)

        var minCost = Long.MAX_VALUE
        for (itemCombination in itemCombinations) {
            val cost = itemCombination.sumOf { it.cost }
            if (cost < minCost) {
                if (playerWins(
                        playerMaxHp = 100,
                        playerDamage = itemCombination.sumOf { it.damage },
                        playerArmor = itemCombination.sumOf { it.armor },
                        bossMaxHp = bossMaxHp,
                        bossDamage = bossDamage,
                        bossArmor = bossArmor

                    )
                ) {
                    minCost = cost.toLong()
                }
            }
        }

        return minCost
    }

    override fun computePart2(input: String): Long {
        val inputLines = input.lines()
        val bossMaxHp = inputLines[0].split(":")[1].trim().toInt()
        val bossDamage = inputLines[1].split(":")[1].trim().toInt()
        val bossArmor = inputLines[2].split(":")[1].trim().toInt()

        val ringsAndWeapons = combineSets(combinationsInRange(RINGS, 0..2), WEAPONS)
        val itemCombinations = ringsAndWeapons + combineSets(ringsAndWeapons, ARMOR)

        var maxCost = Long.MIN_VALUE
        for (itemCombination in itemCombinations) {
            val cost = itemCombination.sumOf { it.cost }
            if (cost > maxCost) {
                if (!playerWins(
                        playerMaxHp = 100,
                        playerDamage = itemCombination.sumOf { it.damage },
                        playerArmor = itemCombination.sumOf { it.armor },
                        bossMaxHp = bossMaxHp,
                        bossDamage = bossDamage,
                        bossArmor = bossArmor

                    )
                ) {
                    maxCost = cost.toLong()
                }
            }
        }

        return maxCost
    }

}
