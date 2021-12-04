package day04

import bindReadInput

val readInput = bindReadInput("day04")

data class Board(val fields: List<List<Int>>) {

    fun numberDrawn(number: Int): Boolean {
        return false
    }

    companion object {
        fun fromString(boardString: String): Board {
            return Board(
                boardString.split("\n", "\r\n")
                    .map { it.split(" ").flatMap {
                        val trimmed = it.trim()
                        if(trimmed.isEmpty()) {
                            listOf()
                        } else {
                            listOf(trimmed.toInt())
                        }
                    } }
            )
        }
    }
}

data class BingoGame(val randomNumbers: List<Int>, val boards: List<Board>) {

    companion object {
        fun fromString(input: String): BingoGame {
            val segments = input.split("\n\n", "\r\n\r\n")
            val randomNumbers = segments[0].split(',').map { it.toInt() }
            val boards = segments.subList(1, segments.size).map { Board.fromString(it) }
            return BingoGame(randomNumbers, boards)
        }
    }
}

data class Part1Result(val winningBoard: Int, val winningRowSum: Int, val lastDrawnNumber: Int, val finalScore: Int = winningBoard*lastDrawnNumber)

fun part1(input: String): Part1Result {
    val game = BingoGame.fromString(input)



    return Part1Result(0,0,0)
}

fun main() {
    val input = readInput("input.txt")

    val part1Result = part1(input)
    println("Part1: $part1Result")



}
