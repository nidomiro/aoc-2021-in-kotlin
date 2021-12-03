package day03

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class Tests : DescribeSpec({
    val input = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
    )

    describe("part1") {
        it("fulfills the given example") {
            val result = part1(input)
            result shouldBe PowerReport(gammaRate = 22, epsilonRate = 9)
            result.multiplied shouldBe 198
        }
    }

    describe("part2") {
        it("fulfills the given example") {
            val result = part2(input)
            result shouldBe LifeSupportReport(oxygenGeneratorRating = 23, co2ScrubberRating = 10)
            result.multiplied shouldBe 230
        }
    }


})