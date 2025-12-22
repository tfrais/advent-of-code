package cz.frais.aoc.year2015.day22

import cz.frais.aoc.year2015.day22.Effect.*

enum class Spell(val manaCost: Int, val effect: Effect?, val operation: (GameState) -> GameState) {

    MAGIC_MISSILE(
        53,
        null,
        { state -> state.copy(bossHp = state.bossHp - 4) }),

    DRAIN(
        73,
        null,
        { state -> state.copy(bossHp = state.bossHp - 2, playerHp = state.playerHp + 2) }),

    SHIELD(
        113,
        SHIELD_EFFECT,
        { state -> state.copy(effectDuration = state.effectDuration + (SHIELD_EFFECT to SHIELD_EFFECT.maxDuration)) }),

    POISON(
        173,
        POISON_EFFECT,
        { state -> state.copy(effectDuration = state.effectDuration + (POISON_EFFECT to POISON_EFFECT.maxDuration)) }),

    RECHARGE(
        229,
        RECHARGE_EFFECT,
        { state -> state.copy(effectDuration = state.effectDuration + (RECHARGE_EFFECT to RECHARGE_EFFECT.maxDuration)) })

}