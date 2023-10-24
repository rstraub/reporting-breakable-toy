package nl.codecraftr.scala.kata

import cats.data.Validated._
import cats.data.ValidatedNel
import cats.implicits._

import scala.util.Try

object Parser {
  sealed trait ParsingError {
    def message: String
  }
  final case class InvalidNumberError(input: String) extends ParsingError {
    override def message: String = s"Invalid number: '$input'"
  }
  final case class InvalidAgeError(age: Int) extends ParsingError {
    override def message: String = s"Age must be a positive number: '$age'"
  }
  final case class NotAllowedToDriveError(age: Age) extends ParsingError {
    override def message: String = s"Age must be at least 18 to drive: '$age'"
  }

  def parse(inputs: String*): ValidatedNel[ParsingError, List[DrivingAge]] =
    parse(
      inputs.toList
    )

  def parse(inputs: List[String]): ValidatedNel[ParsingError, List[DrivingAge]] =
    inputs
      .map(parse)
      .traverse(_.toValidatedNel)

  private def parse(input: String): Either[ParsingError, DrivingAge] =
    for {
      number <- parseNumber(input)
      age <- parseAge(number)
      drivingAge <- parseDrivingAge(age)
    } yield drivingAge

  private def parseNumber(input: String): Either[InvalidNumberError, Int] = Try(
    input.toInt
  ).toEither.leftMap(_ => InvalidNumberError(input))

  // TODO explore way to move this near Age
  private def parseAge(age: Int): Either[InvalidAgeError, Age] =
    if (age < 0) Left(InvalidAgeError(age)) else Right(Age(age))

  // TODO how to move this near DrivingAge?
  // TODO how could we not duplicate the data model between Age -> DrivingAge
  private def parseDrivingAge(
      age: Age
  ): Either[NotAllowedToDriveError, DrivingAge] =
    if (age.value < 18) Left(NotAllowedToDriveError(age))
    else Right(DrivingAge(age.value))
}
