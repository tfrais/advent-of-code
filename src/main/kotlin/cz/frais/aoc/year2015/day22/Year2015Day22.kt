package cz.frais.aoc.year2015.day22

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2015Day22 : AdventOfCodeDaySolution {

    fun compute(input: String, hardMode: Boolean): Long {
        var min = Long.MAX_VALUE
        val queue = ArrayDeque<GameState>()
        queue.add(
            GameState(
                playersTurn = true,
                playerHp = 50,
                mana = 500,
                totalManaSpent = 0,
                playerArmor = 0,
                bossHp = input.lines()[0].split(":")[1].trim().toInt(),
                bossDamage = input.lines()[1].split(":")[1].trim().toInt(),
                effectDuration = mapOf()
            )
        )

        while (queue.isNotEmpty()) {
            var current = queue.removeFirst()
            if (current.totalManaSpent >= min) continue
            current = current.applyEffects()

            if (hardMode && current.playersTurn) {
                current = current.copy(playerHp = current.playerHp - 1)
            }

            if (current.isLoseCondition()) continue

            if (current.isWinCondition()) {
                if (current.totalManaSpent <= min) {
                    min = current.totalManaSpent.toLong()
                }
                continue
            }

            if (!current.playersTurn) {
                queue += current.nextStateAfterBossTurn()
            } else {
                for (spell in Spell.entries) {
                    if (current.mana >= spell.manaCost && (spell.effect == null || spell.effect !in current.effectDuration.keys)) {
                        queue += current.nextStateAfterPlayerTurn(current, spell)
                    }
                }
            }

        }

        return min
    }

    override fun computePart1(input: String) = compute(input, false)

    override fun computePart2(input: String) = compute(input, true)

}
