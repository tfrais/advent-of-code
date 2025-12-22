package cz.frais.aoc.year2015.day22

enum class Effect(val maxDuration: Int, val operation: (GameState) -> GameState) {

    SHIELD_EFFECT(6, { state -> state.copy(playerArmor = 7) }),
    POISON_EFFECT(6, { state -> state.copy(bossHp = state.bossHp - 3) }),
    RECHARGE_EFFECT(5, { state -> state.copy(mana = state.mana + 101) }),

}
