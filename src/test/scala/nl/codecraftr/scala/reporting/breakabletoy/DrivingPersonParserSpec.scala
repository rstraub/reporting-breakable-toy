package nl.codecraftr.scala.reporting.breakabletoy

import cats.data.NonEmptyChain
import cats.data.Validated.{Invalid, Valid}
import nl.codecraftr.scala.reporting.breakabletoy.ParsingError.{
  DUIParseError,
  NotAllowedToDriveError
}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DrivingPersonParserSpec extends AnyFlatSpec with Matchers {
  it should "parse a person given a valid age and DUI" in {
    DrivingPersonParser.parse("21", "y") shouldBe Valid(
      DrivingPerson(DrivingAge(Age(21)), hasDUI = true)
    )
  }

  it should "parse a person given a invalid age and invalid DUI" in {
    DrivingPersonParser.parse("17", "x") shouldBe
      Invalid(
        NonEmptyChain.of(
          NotAllowedToDriveError(Age(17)),
          DUIParseError("x")
        )
      )
  }
}
