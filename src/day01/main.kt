package day01

import bindReadInput

val readInput = bindReadInput("day01")

fun main() {
    val input = readInput("input.txt")
    val depths = input.map { it.toInt()}

    fun part1(input: List<Int>): Int {

        return input.windowed(2).flatMap {
            val (last, current) = it
            val increase = current - last
            if (increase > 0){
                listOf(1)
            } else {
                listOf()
            }
        }.count()

    }

    val part1Result = part1(depths)
    println("Part1: $part1Result")



    fun part2(input: List<Int>): Int {
        val valueWindows = input.windowed(3)
        val windowSums = valueWindows.map { it.sum() }
        val diffWindows = windowSums.windowed(2)

        return diffWindows.flatMap {
            val (last, current) = it
            val increase = current - last
            if (increase > 0){
                listOf(1)
            } else {
                listOf()
            }
        }.count()
    }

    val part2Result = part2(depths)
    println("Part2: $part2Result")

}
