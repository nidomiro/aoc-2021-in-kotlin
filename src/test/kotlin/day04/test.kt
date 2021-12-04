package day04

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class Tests : DescribeSpec({
    val input = """
        7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1
        
        22 13 17 11  0
         8  2 23  4 24
        21  9 14 16  7
         6 10  3 18  5
         1 12 20 15 19
        
         3 15  0  2 22
         9 18 13 17  5
        19  8  7 25 23
        20 11 10 24  4
        14 21 16 12  6
        
        14 21 17 24  4
        10 16 15  9 19
        18  8 23 26 20
        22 11 13  6  5
         2  0 12  3  7
        """.trimIndent()

    it("parses Board correctly") {
        val result = Board.fromString(
            """
                    22 13 17 11  0
                     8  2 23  4 24
                    21  9 14 16  7
                     6 10  3 18  5
                     1 12 20 15 19
            """.trimIndent()
        )
        result shouldBe Board(
            listOf(
                listOf(22, 13, 17, 11, 0),
                listOf(8, 2, 23, 4, 24),
                listOf(21, 9, 14, 16, 7),
                listOf(6, 10, 3, 18, 5),
                listOf(1, 12, 20, 15, 19),
            )
        )
    }

    it("Parses game correctly") {
        val result = BingoGame.fromString(input)
        result shouldBe BingoGame(
            listOf(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1),
            listOf(
                Board.fromString(
                    """
                    22 13 17 11  0
                     8  2 23  4 24
                    21  9 14 16  7
                     6 10  3 18  5
                     1 12 20 15 19
                """.trimIndent()
                ),
                Board.fromString(
                    """
                     3 15  0  2 22
                     9 18 13 17  5
                    19  8  7 25 23
                    20 11 10 24  4
                    14 21 16 12  6
                """.trimIndent()
                ),
                Board.fromString(
                    """
                    14 21 17 24  4
                    10 16 15  9 19
                    18  8 23 26 20
                    22 11 13  6  5
                     2  0 12  3  7
                """.trimIndent()
                ),
            )
        )
    }

    describe("part1") {

        it("fulfills the given example") {
            val result = part1(input)
            result shouldBe Part1Result(188, 24, 4512)
        }
    }

    describe("part2") {

        it("fulfills the given example") {
            val result = part2(input)
            result shouldBe Part2Result(148, 13, 1924)
        }
    }


})