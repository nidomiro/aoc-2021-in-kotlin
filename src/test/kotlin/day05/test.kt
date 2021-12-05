package day05

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class Tests : DescribeSpec({
    val input = listOf(
        "0,9 -> 5,9",
        "8,0 -> 0,8",
        "9,4 -> 3,4",
        "2,2 -> 2,1",
        "7,0 -> 7,4",
        "6,4 -> 2,0",
        "0,9 -> 2,9",
        "3,4 -> 1,4",
        "0,0 -> 8,8",
        "5,5 -> 8,2"
    )

    it("Line parsing works") {
        val result = Line.fromString(input[0])
        result shouldBe Line(Point(0, 9), Point(5, 9))
    }

    it("Point substraction") {
        val result = Point(5, 4) - Point(1, 2)
        result shouldBe Point(4, 2)
    }

    describe("part1") {

        it("fulfills the given example") {
            val result = part1(input)
            result shouldBe Part1Result(5)
        }
    }


})