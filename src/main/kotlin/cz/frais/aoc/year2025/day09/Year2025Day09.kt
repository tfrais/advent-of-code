package cz.frais.aoc.year2025.day09

import cz.frais.aoc.AdventOfCodeDaySolution
import cz.frais.aoc.structures.Position
import kotlin.math.abs

object Year2025Day09 : AdventOfCodeDaySolution {

    override fun computePart1(input: String): Long {
        val corners = input.lines().map { line ->
            val lineSplit = line.split(",")
            Pair(lineSplit[0].toLong(), lineSplit[1].toLong())
        }

        var maxArea = 0L
        for (i in 0 until corners.size) {
            for (j in i + 1 until corners.size) {
                val area = abs(corners[i].first - corners[j].first + 1) * abs(corners[i].second - corners[j].second + 1)
                if (area > maxArea) {
                    maxArea = area
                }
            }
        }

        return maxArea
    }

    sealed class Segment {
        data class Vertical(val x: Int, val y1: Int, val y2: Int) : Segment()
        data class Horizontal(val y: Int, val x1: Int, val x2: Int) : Segment()
    }

    fun buildSegments(vertices: List<Position>): List<Segment> {
        val result = mutableListOf<Segment>()
        for (i in vertices.indices) {
            val a = vertices[i]
            val b = vertices[(i + 1) % vertices.size]
            result += if (a.x == b.x) {
                Segment.Vertical(
                    a.x,
                    minOf(a.y, b.y),
                    maxOf(a.y, b.y)
                )
            } else {
                Segment.Horizontal(
                    a.y,
                    minOf(a.x, b.x),
                    maxOf(a.x, b.x)
                )
            }
        }
        return result
    }

    fun positionOnSegment(p: Position, s: Segment): Boolean =
        when (s) {
            is Segment.Vertical -> p.x == s.x && p.y in s.y1..s.y2
            is Segment.Horizontal -> p.y == s.y && p.x in s.x1..s.x2
        }

    fun isInsideOrOn(p: Position, segments: List<Segment>, verticals: List<Segment.Vertical>): Boolean {
        for (s in segments) if (positionOnSegment(p, s)) return true

        var crossings = 0
        for (v in verticals) {
            if (p.y >= v.y1 && p.y < v.y2 && v.x > p.x) crossings++
        }
        return crossings % 2 == 1
    }

    fun allCornersInsideOrOn(
        xmin: Int, ymin: Int,
        xmax: Int, ymax: Int,
        segments: List<Segment>,
        verticals: List<Segment.Vertical>
    ): Boolean =
        isInsideOrOn(Position(xmin, ymin), segments, verticals) &&
                isInsideOrOn(Position(xmin, ymax), segments, verticals) &&
                isInsideOrOn(Position(xmax, ymin), segments, verticals) &&
                isInsideOrOn(Position(xmax, ymax), segments, verticals)

    fun verticalCrossesHorizontal(
        vx: Int, vy1: Int, vy2: Int,
        ry: Int, rx1: Int, rx2: Int
    ): Boolean = vx in (rx1 + 1)..<rx2 && ry > vy1 && ry < vy2

    fun horizontalCrossesVertical(
        hy: Int, hx1: Int, hx2: Int,
        rx: Int, ry1: Int, ry2: Int
    ): Boolean = hy in (ry1 + 1)..<ry2 && rx > hx1 && rx < hx2

    fun rectangleCrossesBoundary(
        xmin: Int, ymin: Int,
        xmax: Int, ymax: Int,
        verticals: List<Segment.Vertical>,
        horizontals: List<Segment.Horizontal>
    ): Boolean {
        for (v in verticals) {
            if (verticalCrossesHorizontal(v.x, v.y1, v.y2, ymin, xmin, xmax)) return true
            if (verticalCrossesHorizontal(v.x, v.y1, v.y2, ymax, xmin, xmax)) return true
        }
        for (h in horizontals) {
            if (horizontalCrossesVertical(h.y, h.x1, h.x2, xmin, ymin, ymax)) return true
            if (horizontalCrossesVertical(h.y, h.x1, h.x2, xmax, ymin, ymax)) return true
        }
        return false
    }


    override fun computePart2(input: String): Long {
        val vertices = input.lines().map {
            val (x, y) = it.split(",")
            Position(x.toInt(), y.toInt())
        }

        val redTiles = vertices.toSet()
        val segments = buildSegments(vertices)
        val verticals = segments.filterIsInstance<Segment.Vertical>()
        val horizontals = segments.filterIsInstance<Segment.Horizontal>()

        val xs = vertices.map { it.x }.distinct().sorted()
        val ys = vertices.map { it.y }.distinct().sorted()

        var largestArea = 0L

        for (i in xs.indices) {
            for (j in i + 1 until xs.size) {
                val xmin = xs[i]
                val xmax = xs[j]
                val width = xmax - xmin + 1

                for (a in ys.indices) {
                    for (b in a + 1 until ys.size) {
                        val ymin = ys[a]
                        val ymax = ys[b]
                        val height = ymax - ymin + 1
                        val area = width.toLong() * height
                        if (area <= largestArea) continue

                        // two opposite corners must be red
                        val diag1 = Position(xmin, ymin) in redTiles && Position(xmax, ymax) in redTiles
                        val diag2 = Position(xmin, ymax) in redTiles && Position(xmax, ymin) in redTiles
                        if (!diag1 && !diag2) continue

                        // all corners inside or on boundary
                        if (!allCornersInsideOrOn(xmin, ymin, xmax, ymax, segments, verticals)) continue

                        // rectangle does not cross polygon boundary
                        if (rectangleCrossesBoundary(xmin, ymin, xmax, ymax, verticals, horizontals)) continue

                        largestArea = area
                    }
                }
            }
        }

        return largestArea
    }

}