package nl.codecraftr.scala.reporting.breakabletoy

import cats.data.Validated._
import cats.data.ValidatedNel
import cats.implicits._
import ParsingError._

import scala.util.Try

// https://typelevel.org/cats/datatypes/validated.html
object Parser {

  def parse(inputs: String*): ValidatedNel[ParsingError, List[DrivingAge]] =
    parse(
      inputs.toList
    )

  def parse(
      inputs: List[String]
  ): ValidatedNel[ParsingError, List[DrivingAge]] =
    inputs
      .map(parse)
      .traverse(_.toValidatedNel)

  // TODO can we make these errors cascade
  private def parse(input: String): Either[ParsingError, DrivingAge] =
    for {
      number <- parseNumber(input)
      age <- parseAge(number)
      drivingAge <- parseDrivingAge(age)
    } yield drivingAge

  private def parseNumber(input: String): Either[InvalidNumberError, Int] = Try(
    input.toInt
  ).toEither.leftMap(_ => InvalidNumberError(input))

  // TODO explore way to move validation closer to Age
  private def parseAge(age: Int): Either[InvalidAgeError, Age] =
    if (age < 0) Left(InvalidAgeError(age)) else Right(Age(age))

  // TODO explore way to move validation closer to DrivingAge
  private def parseDrivingAge(
      age: Age
  ): Either[NotAllowedToDriveError, DrivingAge] =
    if (age.value < 18) Left(NotAllowedToDriveError(age))
    else Right(DrivingAge(age.value))
}
