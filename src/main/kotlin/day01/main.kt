package day01

import bindReadInputLines

val readInput = bindReadInputLines("day01")

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

fun part1(input: List<String>): Int {
    val depths = input.asSequence().map { it.toInt() }
    return depths.increasingValuesCount()
}

fun part2(input: List<String>): Int {
    val depths = input.asSequence().map { it.toInt() }
    val valueWindows = depths.windowed(3)
    val windowSums = valueWindows.map { it.sum() }
    return windowSums.increasingValuesCount()
}

fun main() {
    val input = readInput("input.txt")

    val part1Result = part1(input)
    println("Part1: $part1Result")


    val part2Result = part2(input)
    println("Part2: $part2Result")

}
