package day01

import bindReadInput

val readInput = bindReadInput("day01")

fun main() {
    val input = readInput("input.txt")
    val depths = input.map { it.toInt() }

    fun Sequence<Int>.increasingValuesCount(): Int {
        return windowed(2).sumOf {
            val (last, current) = it
            val increase = current - last
            if (increase > 0) {
                1 as Int
            } else {
                0
            }
        }
    }

    fun part1(input: List<Int>): Int {
        return input.asSequence().increasingValuesCount()
    }

    val part1Result = part1(depths)
    println("Part1: $part1Result")

    fun part2(input: List<Int>): Int {
        val valueWindows = input.asSequence().windowed(3)
        val windowSums = valueWindows.map { it.sum() }
        return windowSums.increasingValuesCount()
    }

    val part2Result = part2(depths)
    println("Part2: $part2Result")

}
