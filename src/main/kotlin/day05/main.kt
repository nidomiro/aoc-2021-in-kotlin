package day05

import bindReadInputLines
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.sign

val readInput = bindReadInputLines("day05")

data class Point(val x: Int, val y: Int) {

    operator fun minus(other: Point): Point {
        return Point(this.x - other.x, this.y - other.y)
    }

    operator fun plus(other: Point): Point {
        return Point(this.x + other.x, this.y + other.y)
    }

    fun calcVerticalOrHorizontalOrDiagonalPointsTo(other: Point): List<Point> {
        return if (this == other) {
            listOf(this)
        } else {
            val direction = other - this
            val signX = direction.x.sign
            val signY = direction.y.sign
            List(max(direction.x.absoluteValue, direction.y.absoluteValue) + 1) { Point(this.x + it * signX, this.y + it * signY) }
        }
    }

}


data class HorVerOrDiagLine(val start: Point, val end: Point) {

    val linePoints = start.calcVerticalOrHorizontalOrDiagonalPointsTo(end)

    val isHorizontal = start.x == end.x
    val isVertical = start.y == end.y

    fun intersectionPoints(other: HorVerOrDiagLine): List<Point> {
        return linePoints.intersect(other.linePoints.toSet()).toList()
    }

    companion object {
        fun fromString(str: String): HorVerOrDiagLine {
            val points = str.split("->").map { pointString -> pointString.trim().split(',').map { it.toInt() } }
            return HorVerOrDiagLine(Point(points[0][0], points[0][1]), Point(points[1][0], points[1][1]))
        }
    }
}

data class Part1Result(val intersectionCount: Int)
data class Part2Result(val intersectionCount: Int)

fun part1(input: List<String>): Part1Result {
    val lines = input
        .map { HorVerOrDiagLine.fromString(it) }
        .filter { it.isHorizontal || it.isVertical }

    val intersectingPoints = lines.flatMapIndexed { index, line1 ->
        lines
            .subList(index + 1, lines.size)
            .flatMap { line2 ->
                line1.intersectionPoints(line2)
            }
    }.toSet()
    return Part1Result(intersectingPoints.count())
}

fun part2(input: List<String>): Part2Result {
    val lines = input
        .map { HorVerOrDiagLine.fromString(it) }

    val intersectingPoints = lines.flatMapIndexed { index, line1 ->
        lines
            .subList(index + 1, lines.size)
            .flatMap { line2 ->
                line1.intersectionPoints(line2)
            }
    }.toSet()
    return Part2Result(intersectingPoints.count())
}

fun main() {
    val input = readInput("input.txt")

    val part1Result = part1(input)
    println("Part1: $part1Result")

    val part2Result = part2(input)
    println("Part2: $part2Result")


}
