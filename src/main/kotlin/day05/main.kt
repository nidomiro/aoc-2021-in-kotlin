package day05

import bindReadInputLines
import kotlin.math.roundToInt

val readInput = bindReadInputLines("day05")

data class Point(val x: Int, val y: Int) {
    constructor(pair: Pair<Int, Int>): this(pair.first, pair.second)

    operator fun minus(other: Point): Point {
        return Point(this.x - other.x, this.y - other.y)
    }

    operator fun plus(other: Point): Point {
        return Point(this.x + other.x, this.y + other.y)
    }


}

operator fun Double.times(other: Point): Point {
    return Point((this * other.x).roundToInt(), (this * other.y).roundToInt())
}


data class Line(val start: Point, val end: Point) {

    val direction = end - start

    val isHorizontal = start.x == end.x
    val isVertical = start.y == end.y

    fun intersectsWith(other: Line): Point? {
        val t = ((start.x - other.start.x)* (other.start.y- other.end.y) - (start.y - other.start.y)*( other.start.x - other.end.x)).toDouble() /
                ((start.x - end.x)*(other.start.y - other.end.x) - (start.y - end.y) * (other.start.x - other.end.x))

        val u = ((start.x - other.start.x)* (start.y- end.y) - (start.y - other.start.y)*( start.x - end.x)).toDouble() /
                ((start.x - end.x)*(other.start.y - other.end.x) - (start.y - end.y) * (other.start.x - other.end.x))

        return if(t in 0.0..1.0 && u in 0.0..1.0) {
            start + t * direction
        } else {
            null
        }
    }

    companion object {
        fun fromString(str: String): Line {
            val points = str.split("->").map { pointString -> pointString.trim().split(',').map { it.toInt() } }
            return Line(Point(points[0][0], points[0][1]), Point(points[1][0], points[1][1]))
        }
    }
}

data class Part1Result(val intersectionCount: Int)

fun part1(input: List<String>): Part1Result {
    val lines = input
        .map { Line.fromString(it) }
        .filter { it.isHorizontal || it.isVertical }

    val intersectingLines = lines.flatMapIndexed { index, line1 ->
        lines
            .subList(index + 1, lines.size)
            .mapNotNull { line2 ->
                val intersectionPoint = line1.intersectsWith(line2)
                if (intersectionPoint != null) {
                    listOf(line1, line2, intersectionPoint)
                } else {
                    null
                }
            }
    }
    return Part1Result(intersectingLines.count())
}

fun main() {
    val input = readInput("input.txt")

    val part1Result = part1(input)
    println("Part1: $part1Result")

//    val part2Result = part2(input)
//    println("Part2: $part2Result")


}
