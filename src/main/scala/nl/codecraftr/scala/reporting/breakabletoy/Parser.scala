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

  private def parse(input: String): Either[ParsingError, DrivingAge] =
    for {
      num <- parseNumber(input)
      age <- parseAge(num)
      drivingAge <- parseDrivingAge(age)
    } yield drivingAge

  private def parseNumber(input: String): Either[InvalidNumberError, Int] =
    Try(
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
    // TODO could we not duplicate the data model between Age -> DrivingAge (age.value)
    else Right(DrivingAge(age.value))
}
