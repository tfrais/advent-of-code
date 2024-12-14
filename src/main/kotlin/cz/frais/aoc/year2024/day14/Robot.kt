package cz.frais.aoc.year2024.day14

import cz.frais.aoc.structures.Position
import cz.frais.aoc.structures.Vector

class Robot(rawString: String) {

    companion object {
        private val PARSING_RE = Regex(
            """^p=(?<px>-?\d+),(?<py>-?\d+)\s+v=(?<vx>-?\d+),(?<vy>-?\d+)$"""
        )
    }

    var currentPosition: Position
    val moveVector: Vector

    init {
        val matchResult = (PARSING_RE.find(rawString) ?: error("Cannot parse $rawString"))
        fun extract(groupName: String): Int = matchResult.groups[groupName]!!.value.toInt()

        currentPosition = Position(extract("px"), extract("py"))
        moveVector = Vector(extract("vx"), extract("vy"))
    }

    fun move(areaWidth: Int, areaHeight: Int, steps: Int) {
        var newX = (currentPosition.x + moveVector.diffX * steps) % areaWidth
        var newY = (currentPosition.y + moveVector.diffY * steps) % areaHeight

        if (newX < 0) {
            newX += areaWidth
        }
        if (newY < 0) {
            newY += areaHeight
        }

        currentPosition = Position(newX, newY)
    }

}