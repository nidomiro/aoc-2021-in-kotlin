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


fun Sequence<String>.calcBitStatistics(): List<BitStatistics> {
    return this.fold(mutableListOf()) {acc, line ->
        if(acc.count() < line.length) {
            acc += MutableList(line.length) {BitStatistics(0,0)}
        }
        for ((index, bit) in line.withIndex()) {
            val statisticsEntry = acc[index]
            when (bit) {
                '0' -> statisticsEntry.zeroCount++
                '1' -> statisticsEntry.oneCount++
                else -> throw IllegalArgumentException("A bit can only be 1 or 0, but was $bit")
            }
            acc[index] = statisticsEntry
        }
        return@fold acc
    }
}


fun part1(input: List<String>): PowerReport {
    val bitPositionStatistics = input.asSequence().calcBitStatistics()
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
