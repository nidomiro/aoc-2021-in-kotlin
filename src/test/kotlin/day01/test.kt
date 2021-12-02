package day01

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class Tests : DescribeSpec({
    val input = listOf(
        "199",
        "200",
        "208",
        "210",
        "200",
        "207",
        "240",
        "269",
        "260",
        "263"
    )
    describe("part1") {
        it("fulfills the given example") {
            val result = part1(input)
            result shouldBe 7
        }
    }

    describe("part2") {
        it("fulfills the given example") {
            val result = part2(input)
            result shouldBe 5
        }

    }


})