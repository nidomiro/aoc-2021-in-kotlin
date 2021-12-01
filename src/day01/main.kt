package day01

import bindReadInput

val readInput = bindReadInput("day01")

enum class Difference{
    NA,
    INCREASED,
    DECREASED
}

fun main() {
    val input = readInput("input.txt")
    val depths = input.map { it.toInt()}

    fun part1(input: List<Int>): Int {

        var lastValue = input.first()
        val differences = depths.map {
            val currentValue = it
            val increase = currentValue - lastValue
            //println("$lastValue -> $currentValue = $increase")
            lastValue = currentValue

            if(increase == 0) {
                Difference.NA
            } else if (increase > 0){
                Difference.INCREASED
            } else {
                Difference.DECREASED
            }
        }

        return differences.count { it == Difference.INCREASED }
    }

    val part1Result = part1(depths)
    println("Part1: $part1Result")


}
