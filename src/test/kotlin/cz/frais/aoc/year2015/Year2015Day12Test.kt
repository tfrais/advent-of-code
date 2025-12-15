package cz.frais.aoc.year2015

import cz.frais.aoc.year2015.day12.Year2015Day12.computePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Year2015Day12Test {

    @Test
    fun testComputePart2() {
        assertThat(computePart2("""[1,2,3]""")).isEqualTo(6)
        assertThat(computePart2("""[1,{"c":"red","b":2},3]""")).isEqualTo(4)
        assertThat(computePart2("""{"d":"red","e":[1,2,3,4],"f":5}""")).isEqualTo(0)
        assertThat(computePart2("""[1,"red",5]""")).isEqualTo(6)

    }

}
