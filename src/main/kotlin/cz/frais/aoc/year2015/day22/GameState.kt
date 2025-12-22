package cz.frais.aoc.year2015.day22

import cz.frais.aoc.year2015.day22.Effect.*
import kotlin.comparisons.maxOf

data class GameState(
    val playersTurn: Boolean,
    val playerHp: Int,
    val mana: Int,
    val totalManaSpent: Int,
    val playerArmor: Int,
    val bossHp: Int,
    val bossDamage: Int,
    val effectDuration: Map<Effect, Int>
) {

    fun applyEffects(): GameState {
        val newEffectDuration = mutableMapOf<Effect, Int>()
        var state = this
        for (effect in this.effectDuration) {
            state = effect.key.operation(state)
            if (effect.value > 1) {
                newEffectDuration[effect.key] = effect.value - 1
            } else if (effect.key == SHIELD_EFFECT) {
                state = state.copy(playerArmor = 0)
            }
        }
        return state.copy(effectDuration = newEffectDuration)
    }

    fun nextStateAfterBossTurn(): GameState {
        require(!this.playersTurn)
        return this.copy(
            playersTurn = true,
            playerHp = this.playerHp - maxOf(this.bossDamage - this.playerArmor, 1)
        )
    }

    fun nextStateAfterPlayerTurn(original: GameState, spell: Spell): GameState {
        require(original.playersTurn)

        return spell.operation(original).copy(
            playersTurn = false,
            mana = original.mana - spell.manaCost,
            totalManaSpent = original.totalManaSpent + spell.manaCost
        )
    }

    fun isWinCondition() = bossHp <= 0

    fun isLoseCondition() = playerHp <= 0

}