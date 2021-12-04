package day04

import bindReadInput

val readInput = bindReadInput("day04")

data class Board(
    val fields: List<List<Int>>,
    val markedFields: MutableList<MutableList<Boolean>> = MutableList(5) { MutableList(5) { false } }
) {

    fun numberDrawn(number: Int): Int? {
        fields.forEachIndexed { rowIndex, row ->
            val columnIndex = row.indexOf(number)
            if (columnIndex >= 0) {
                markedFields[rowIndex][columnIndex] = true
            }
        }
        println("===============")
        markedFields.forEach { row ->
            println(row.joinToString())
        }
        println("===============")

        val winningRowIndex = markedFields.indexOfFirst { row -> row.all { it } }
        if(winningRowIndex >= 0) {
            return sumOfUnmarkedNumbers()
        }

        fun calcWinningColumnIndex(): Int? {
            var index: Int? = null
            for(colIndex in 0..4) {
                var colComplete = true
                for (rowIndex in 0..4) {
                    colComplete = colComplete && markedFields[rowIndex][colIndex]
                }
                if(colComplete) {
                    index = colIndex
                    break
                }
            }
            return index
        }

        val winningColumnIndex= calcWinningColumnIndex()
        if(winningColumnIndex != null) {
            return sumOfUnmarkedNumbers()
        }


        return null
    }

    fun sumOfUnmarkedNumbers(): Int {
        var sum = 0
        for(rowIndex in 0..4) {
            for (colIndex in 0..4) {
                if(!markedFields[rowIndex][colIndex]) {
                    sum += fields[rowIndex][colIndex]
                }
            }
        }
        return sum
    }


    companion object {
        fun fromString(boardString: String): Board {
            return Board(
                boardString.split("\n", "\r\n")
                    .map {
                        it.split(" ").flatMap {
                            val trimmed = it.trim()
                            if (trimmed.isEmpty()) {
                                listOf()
                            } else {
                                listOf(trimmed.toInt())
                            }
                        }
                    }
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

data class Part1Result(val winningBoardUnmarkedNumbersSum: Int, val lastDrawnNumber: Int, val finalScore: Int = winningBoardUnmarkedNumbersSum * lastDrawnNumber)

fun part1(input: String): Part1Result? {
    val game = BingoGame.fromString(input)

    return game.randomNumbers.fold(null as Part1Result?) { acc, number ->
        if (acc != null) {
            return@fold acc
        }
        println("@@@@ New Number drawn: $number @@@@")

        val winningBoards = game.boards.mapNotNull { it.numberDrawn(number) }
        println("")
        if (winningBoards.isEmpty()) {
            null
        } else {
            Part1Result(winningBoards[0], number)
        }

    }
}

data class Part2Result(val loosingBoardUnmarkedNumbersSum: Int, val lastDrawnNumber: Int, val finalScore: Int = loosingBoardUnmarkedNumbersSum * lastDrawnNumber)

fun part2(input: String): Part2Result {
    val game = BingoGame.fromString(input)

    var boards = game.boards
    var lastNumber = -1
    var lastWinningBoard: Board? = null
    for(number in game.randomNumbers) {
        lastNumber = number
        val loosingBoards = boards.filter { it.numberDrawn(number) == null }
        if(loosingBoards.isEmpty()) {
            lastWinningBoard = boards.first()
            break
        }
        boards = loosingBoards
    }

    return Part2Result(lastWinningBoard?.sumOfUnmarkedNumbers() ?: -1, lastNumber)
}

fun main() {
    val input = readInput("input.txt")

    val part1Result = part1(input)
    println("Part1: $part1Result")

    val part2Result = part2(input)
    println("Part2: $part2Result")


}
