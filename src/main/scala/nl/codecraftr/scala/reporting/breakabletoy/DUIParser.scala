package nl.codecraftr.scala.reporting.breakabletoy

import cats.data.{Validated, ValidatedNec}
import cats.data.Validated.{Invalid, Valid}
import cats.implicits._
import nl.codecraftr.scala.reporting.breakabletoy.ParsingError.DUIParseError

object DUIParser {
  def parse(input: String): ValidatedNec[DUIParseError, Boolean] =
    input match {
      case "y" => true.validNec
      case "n" => false.validNec
      case _   => DUIParseError(input).invalidNec
    }

}
