package nl.codecraftr.scala.reporting.breakabletoy

import cats.data.Validated._
import cats.data.{Validated, ValidatedNec}
import cats.implicits._
import nl.codecraftr.scala.reporting.breakabletoy.ParsingError._

import scala.util.Try

// https://typelevel.org/cats/datatypes/validated.html
object AgeParser {
  def parse(
      inputs: List[String]
  ): ValidatedNec[ParsingError, List[DrivingAge]] =
    inputs
      .map(parse)
      .sequence

  def parse(input: String): ValidatedNec[ParsingError, DrivingAge] = {
    val result = for {
      num <- parseNumber(input)
      age <- Age.from(num)
      drivingAge <- DrivingAge.from(age)
    } yield drivingAge
    result.toValidatedNec
  }

  private def parseNumber(input: String): Either[InvalidNumberError, Int] =
    Try(
      input.toInt
    ).toEither.leftMap(_ => InvalidNumberError(input))

}
