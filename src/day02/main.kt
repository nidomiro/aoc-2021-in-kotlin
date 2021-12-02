package day02

import bindReadInput

val readInput = bindReadInput("day02")

sealed class Direction {
    data class Forward(val amount: Int) : Direction()
    data class Up(val amount: Int) : Direction()
    data class Down(val amount: Int) : Direction()
}

fun main() {
    val input = readInput("input.txt")

    fun Sequence<String>.toDirections(): Sequence<Direction> {
        return map {
            val (rawDirection, amount) = it.split(' ', limit = 2)
            return@map when (rawDirection) {
                "forward" -> Direction.Forward(amount.toInt())
                "up" -> Direction.Up(amount.toInt())
                "down" -> Direction.Down(amount.toInt())
                else -> throw IllegalArgumentException("'$rawDirection' is not a valid Direction")
            }
        }

    }

    val directions = input.asSequence().toDirections()

    fun part1(input: Sequence<Direction>): Pair<Int, Int> {
        return input.fold(0 to 0) { acc, direction ->
            val (x, depth) = acc
            when (direction) {
                is Direction.Forward -> (x + direction.amount) to depth
                is Direction.Up -> (x) to (depth - direction.amount)
                is Direction.Down -> (x) to (depth + direction.amount)
            }
        }
    }

    val part1Result = part1(directions)
    println("Part1: Coordinates (x, depth): $part1Result")
    println("Part1: Multiplied: ${part1Result.first * part1Result.second}")

//    fun part2(input: List<Int>): Int {
//        val valueWindows = input.asSequence().windowed(3)
//        val windowSums = valueWindows.map { it.sum() }
//        return windowSums.increasingValuesCount()
//    }
//
//    val part2Result = part2(depths)
//    println("Part2: $part2Result")

}
