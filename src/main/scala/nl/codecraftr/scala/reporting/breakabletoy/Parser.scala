package nl.codecraftr.scala.reporting.breakabletoy

import cats.data.Validated._
import cats.data.{Validated, ValidatedNec}
import cats.implicits._
import cats.syntax._
import nl.codecraftr.scala.reporting.breakabletoy.ParsingError._

import scala.util.Try

// https://typelevel.org/cats/datatypes/validated.html
object Parser {

  def parse(inputs: String*): ValidatedNec[ParsingError, List[DrivingAge]] =
    parse(
      inputs.toList
    )

  def parse(
      inputs: List[String]
  ): ValidatedNec[ParsingError, List[DrivingAge]] =
    inputs
      .map(parse)
      .traverse(_.toValidatedNec)

  private def parse(input: String): Validated[ParsingError, DrivingAge] =
    parseNumber(input).andThen(parseAge).andThen(parseDrivingAge)

  private def parseNumber(input: String): Validated[InvalidNumberError, Int] =
    Try(
      input.toInt
    ).toValidated.leftMap(_ => InvalidNumberError(input))

  // TODO explore way to move validation closer to Age
  private def parseAge(age: Int): Validated[InvalidAgeError, Age] =
    if (age < 0) Invalid(InvalidAgeError(age)) else Valid(Age(age))

  // TODO explore way to move validation closer to DrivingAge
  private def parseDrivingAge(
      age: Age
  ): Validated[NotAllowedToDriveError, DrivingAge] =
    if (age.value < 18) Invalid(NotAllowedToDriveError(age))
    else Valid(DrivingAge(age.value))
}
