package day02

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MyTests : DescribeSpec({
    describe("part1") {
        it("fulfills the given example") {
            val input = listOf(
                "forward 5",
                "down 5",
                "forward 8",
                "up 3",
                "down 8",
                "forward 2"
            )

            val result = part1(input)

            result shouldBe (15 to 10)
        }

    }

    describe("part2") {
        it("fulfills the given example") {
            val input = listOf(
                "forward 5",
                "down 5",
                "forward 8",
                "up 3",
                "down 8",
                "forward 2"
            )

            val result = part2(input)

            result.x shouldBe 15
            result.depth shouldBe 60
        }

    }


})