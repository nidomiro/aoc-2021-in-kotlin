package day03

import bindReadInputLines

val readInput = bindReadInputLines("day03")

data class PowerReport(
    val gammaRate: Int,
    val epsilonRate: Int,
) {
    val multiplied = gammaRate * epsilonRate
}

data class LifeSupportReport(
    val oxygenGeneratorRating: Int,
    val co2ScrubberRating: Int,
) {
    val multiplied = oxygenGeneratorRating * co2ScrubberRating
}

data class BitStatistics(var zeroCount: Int, var oneCount: Int) {
    val mostCommonBit: Char?
        get() = when {
            zeroCount > oneCount -> '0'
            zeroCount < oneCount -> '1'
            else -> null
        }
    val leastCommonBit: Char?
        get() = when {
            zeroCount > oneCount -> '1'
            zeroCount < oneCount -> '0'
            else -> null
        }
}


fun List<String>.calcBitStatistics(): List<BitStatistics> {
    return this.fold(mutableListOf()) { acc, line ->
        if (acc.count() < line.length) {
            acc += MutableList(line.length) { BitStatistics(0, 0) }
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
    val bitPositionStatistics = input.calcBitStatistics()
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

fun part2(input: List<String>): LifeSupportReport {

    fun calcBitValue(input: List<String>, bitMatchesAtPosition: (bit: Char, bitStatistics: BitStatistics) -> Boolean): Int {
        var filteredInput = input
        var bitPositionStatistics =
            filteredInput.calcBitStatistics() // Could be optimized to calc the statistics only for the first position
        var position = 0
        while (filteredInput.count() > 1) {
            filteredInput = filteredInput.filter {
                val statistics = bitPositionStatistics[position]
                val bit = it[position]
                bitMatchesAtPosition(bit, statistics)
            }
            bitPositionStatistics =
                filteredInput.calcBitStatistics() // Could be optimized to calc the statistics only for the given position
            position++
        }
        return filteredInput.first().toInt(2)
    }

    val oxygenGeneratorRating = calcBitValue(input) { bit, statistics ->
        val mostCommonBit = statistics.mostCommonBit

        return@calcBitValue when {
            mostCommonBit == null && bit == '1' -> true
            mostCommonBit == '1' && bit == '1' -> true
            mostCommonBit == '0' && bit == '0' -> true
            else -> false
        }
    }

    val co2ScrubberRating = calcBitValue(input) { bit, statistics ->
        val leastCommonBit = statistics.leastCommonBit

        return@calcBitValue when {
            leastCommonBit == null && bit == '0' -> true
            leastCommonBit == '1' && bit == '1' -> true
            leastCommonBit == '0' && bit == '0' -> true
            else -> false
        }
    }

    return LifeSupportReport(oxygenGeneratorRating, co2ScrubberRating)
}

fun main() {
    val input = readInput("input.txt")

    val part1Result = part1(input)
    println("Part1: $part1Result, Multiplied: ${part1Result.multiplied}")

    val part2Result = part2(input)
    println("Part2: $part2Result, Multiplied: ${part2Result.multiplied}")


}
