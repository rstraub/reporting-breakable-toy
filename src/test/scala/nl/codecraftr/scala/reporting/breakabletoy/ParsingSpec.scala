package nl.codecraftr.scala.reporting.breakabletoy

import cats.data.NonEmptyList
import cats.data.Validated.{Invalid, Valid}
import ParsingError._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ParsingSpec extends AnyFlatSpec with Matchers {
  it should "allow successful results given valid inputs" in {
    val result = Parser.parse("21", "32")

    result shouldBe Valid(Seq(DrivingAge(21), DrivingAge(32)))
  }

  it should "accumulate errors about input in the wrong format" in {
    val result = Parser.parse("c", "18", "a")

    result shouldBe Invalid(
      NonEmptyList.of(
        InvalidNumberError("c"),
        InvalidNumberError("a")
      )
    )
  }

  it should "accumulate errors about input being an invalid age" in {
    val result = Parser.parse("21", "-1")

    result shouldBe Invalid(
      NonEmptyList.of(
        InvalidAgeError(-1)
      )
    )
  }

  it should "accumulate errors about input being invalid driving age" in {
    val result = Parser.parse("17")

    result shouldBe Invalid(
      NonEmptyList.of(
        NotAllowedToDriveError(Age(17))
      )
    )
  }

  it should "accumulate errors per input" in {
    val result = Parser.parse("c", "-1", "17")

    result shouldBe Invalid(
      NonEmptyList.of(
        InvalidNumberError("c"),
        InvalidAgeError(-1),
        NotAllowedToDriveError(Age(17))
      )
    )
  }
}
