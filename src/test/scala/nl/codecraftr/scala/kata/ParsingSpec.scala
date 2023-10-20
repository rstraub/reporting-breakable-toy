package nl.codecraftr.scala.kata

import cats.data.NonEmptyList
import cats.data.Validated.{Invalid, Valid}
import nl.codecraftr.scala.kata.Parser.ParsingError
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ParsingSpec extends AnyFlatSpec with Matchers {
  it should "accumulate problems in a transformation" in {
    val result = Parser.parse("c", "1", "2", "a")

    result shouldBe Invalid(
      NonEmptyList.of(
        ParsingError("c"),
        ParsingError("a")
      )
    )
  }

  it should "allow successful results given valid inputs" in {
    val result = Parser.parse("1", "2", "3")

    result shouldBe Valid(Seq(1, 2, 3))
  }
}
