package day03

import bindReadInput

val readInput = bindReadInput("day03")

data class PowerReport(
    val gammaRate: Int,
    val epsilonRate: Int,
) {
    val multiplied = gammaRate * epsilonRate
}

data class BitStatistics(var zeroCount: Int, var oneCount: Int)


fun part1(input: List<String>): PowerReport {
    val bitPositionStatistics = MutableList<BitStatistics>(input[0].length) {BitStatistics(0,0)}
    for (reading in input) {

        for ((index, bit) in reading.withIndex()) {
            val statisticsEntry = bitPositionStatistics.getOrElse(index) { BitStatistics(0, 0) }
            when (bit) {
                '0' -> statisticsEntry.zeroCount++
                '1' -> statisticsEntry.oneCount++
                else -> throw IllegalArgumentException("A bit can only be 1 or 0, but was $bit")
            }
            bitPositionStatistics[index] = statisticsEntry
        }
    }
    val gammaRateInBits = bitPositionStatistics.fold("") { acc, bitStatistics ->
        when {
            bitStatistics.oneCount > bitStatistics.zeroCount -> acc + "1"
            else -> acc + "0"
        }
    }
    val epsilonRateInBits: String = gammaRateInBits.map {
        when (it) {
            '0' -> "1"
            '1' -> "0"
            else -> ""
        }
    }.joinToString("")
    return PowerReport(gammaRate = gammaRateInBits.toInt(2), epsilonRate = epsilonRateInBits.toInt(2))
}

fun main() {
    val input = readInput("input.txt")

    val part1Result = part1(input)
    println("Part1: $part1Result, Multiplied: ${part1Result.multiplied}")


}
