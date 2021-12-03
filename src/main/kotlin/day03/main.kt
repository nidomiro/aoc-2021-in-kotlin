package day03

import bindReadInput

val readInput = bindReadInput("day03")

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



    fun calcOxygenGeneratorRating(input: List<String>): Int {

        fun bitMatchesOxygenGeneratorRatingAtPosition(position: Int, line: String, bitPositionStatistics: List<BitStatistics>): Boolean {
            val statistics = bitPositionStatistics[position]
            val mostCommonBit = statistics.mostCommonBit

            val bit = line[position]

            return when {
                mostCommonBit == null && bit == '1' -> true
                mostCommonBit == '1' && bit == '1' -> true
                mostCommonBit == '0' && bit == '0' -> true
                else -> false
            }
        }

        var oxygenFilteredInput = input
        var bitPositionStatistics = oxygenFilteredInput.calcBitStatistics()
        var position = 0
        while (oxygenFilteredInput.count() > 1) {
            oxygenFilteredInput = oxygenFilteredInput.filter {
                bitMatchesOxygenGeneratorRatingAtPosition(position, it, bitPositionStatistics)
            }
            bitPositionStatistics = oxygenFilteredInput.calcBitStatistics()
            position++
        }
        return oxygenFilteredInput.first().toInt(2)
    }

    fun calcCO2ScrubberRating(input: List<String>): Int {

        fun bitMatchesAtPosition(position: Int, line: String, bitPositionStatistics: List<BitStatistics>): Boolean {
            val statistics = bitPositionStatistics[position]
            val leastCommonBit = statistics.leastCommonBit

            val bit = line[position]

            return when {
                leastCommonBit == null && bit == '0' -> true
                leastCommonBit == '1' && bit == '1' -> true
                leastCommonBit == '0' && bit == '0' -> true
                else -> false
            }
        }

        var filteredInput = input
        var bitPositionStatistics = filteredInput.calcBitStatistics()
        var position = 0
        while (filteredInput.count() > 1) {
            filteredInput = filteredInput.filter {
                bitMatchesAtPosition(position, it, bitPositionStatistics)
            }
            bitPositionStatistics = filteredInput.calcBitStatistics()
            position++
        }
        return filteredInput.first().toInt(2)
    }



    return LifeSupportReport(oxygenGeneratorRating = calcOxygenGeneratorRating(input), co2ScrubberRating = calcCO2ScrubberRating(input))
}

fun main() {
    val input = readInput("input.txt")

    val part1Result = part1(input)
    println("Part1: $part1Result, Multiplied: ${part1Result.multiplied}")

    val part2Result = part2(input)
    println("Part1: $part2Result, Multiplied: ${part2Result.multiplied}")


}
