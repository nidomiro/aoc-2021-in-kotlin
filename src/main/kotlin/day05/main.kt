package day05

import bindReadInputLines
import kotlin.math.abs
import kotlin.math.roundToInt

val readInput = bindReadInputLines("day05")

data class Point(val x: Int, val y: Int) {

    operator fun minus(other: Point): Point {
        return Point(this.x - other.x, this.y - other.y)
    }

    operator fun plus(other: Point): Point {
        return Point(this.x + other.x, this.y + other.y)
    }

    fun calcVerticalOrHorizontalPointsTo(other: Point): List<Point> {
        val direction = other - this
        return if (abs(direction.x) == 0 && abs(direction.y) == 0) {
            listOf(this)
        } else if (abs(direction.x) > 0) {
            val sign = if (direction.x < 0) -1 else 1
            List(abs(direction.x) + 1) { Point(this.x + it * sign, this.y) }
        } else {
            val sign = if (direction.y < 0) -1 else 1
            List(abs(direction.y) + 1) { Point(this.x, this.y + it * sign) }
        }

    }

    fun calcVerticalOrHorizontalOrDiagonalPointsTo(other: Point): List<Point> {
        val direction = other - this
        return if (abs(direction.x) == abs(direction.y) && abs(direction.x) != 0) {
            val signX = if (direction.x < 0) -1 else 1
            val signY = if (direction.y < 0) -1 else 1
            List(abs(direction.x) + 1) { Point(this.x + it * signX, this.y + it * signY) }
        } else {
           calcVerticalOrHorizontalPointsTo(other)
        }

    }


}

operator fun Double.times(other: Point): Point {
    return Point((this * other.x).roundToInt(), (this * other.y).roundToInt())
}


data class HorOrVerLine(val start: Point, val end: Point) {

    val linePoints = start.calcVerticalOrHorizontalOrDiagonalPointsTo(end)

    val isHorizontal = start.x == end.x
    val isVertical = start.y == end.y

    fun intersectionPoints(other: HorOrVerLine): List<Point> {
        return linePoints.intersect(other.linePoints.toSet()).toList()
    }

    companion object {
        fun fromString(str: String): HorOrVerLine {
            val points = str.split("->").map { pointString -> pointString.trim().split(',').map { it.toInt() } }
            return HorOrVerLine(Point(points[0][0], points[0][1]), Point(points[1][0], points[1][1]))
        }
    }
}

data class Part1Result(val intersectionCount: Int)
data class Part2Result(val intersectionCount: Int)

fun part1(input: List<String>): Part1Result {
    val lines = input
        .map { HorOrVerLine.fromString(it) }
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
        .map { HorOrVerLine.fromString(it) }

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
