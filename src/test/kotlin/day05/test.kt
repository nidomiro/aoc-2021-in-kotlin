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
        val result = HorVerOrDiagLine.fromString(input[0])
        result shouldBe HorVerOrDiagLine(Point(0, 9), Point(5, 9))
    }

    it("Point substraction") {
        val result = Point(5, 4) - Point(1, 2)
        result shouldBe Point(4, 2)
    }

    it("Line Points") {

        val line = HorVerOrDiagLine.fromString("3,4 -> 1,4")
        println(line.linePoints)

        line.linePoints shouldBe listOf(
            Point(3, 4),
            Point(2, 4),
            Point(1, 4),
        )
    }

    it("Line Intersection") {
        val line1 = HorVerOrDiagLine.fromString("0,9 -> 3,9")
        val line2 = HorVerOrDiagLine.fromString("0,9 -> 5,9")
        line1.intersectionPoints(line2) shouldBe listOf(
            Point(0, 9),
            Point(1, 9),
            Point(2, 9),
            Point(3, 9),
        )
    }

    it("Line Intersection2") {
        val line1 = HorVerOrDiagLine.fromString("9,4 -> 3,4")
        println(line1.linePoints)
        val line2 = HorVerOrDiagLine.fromString("3,4 -> 1,4")
        println(line2.linePoints)

        line1.intersectionPoints(line2) shouldBe listOf(
            Point(3, 4)
        )
    }

    describe("part1") {

        it("fulfills the given example") {
            val result = part1(input)
            result shouldBe Part1Result(5)
        }
    }

    describe("part2") {

        it("fulfills the given example") {
            val result = part2(input)
            result shouldBe Part2Result(12)
        }
    }


})