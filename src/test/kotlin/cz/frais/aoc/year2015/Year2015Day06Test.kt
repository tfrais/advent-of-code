package cz.frais.aoc.year2015

import cz.frais.aoc.year2015.day06.Instruction
import cz.frais.aoc.year2015.day06.InstructionParser
import cz.frais.aoc.year2015.day06.InstructionType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2015Day06Test {

    @Test
    fun testInstructionParser() {
        assertThat(InstructionParser.parse("turn on 489,959 through 759,964"))
            .usingRecursiveComparison().isEqualTo(
                Instruction(
                    InstructionType.TURN_ON,
                    IntRange(489, 759),
                    IntRange(959, 964)
                )
            )
    }

}
