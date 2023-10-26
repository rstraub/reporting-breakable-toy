package nl.codecraftr.scala.reporting.breakabletoy

import cats.data.Validated
import cats.data.Validated.Valid
import nl.codecraftr.scala.reporting.breakabletoy.ParsingError.DUIParseError

object DUIParser {
  def parse(input: String): Validated[DUIParseError, Boolean] =
    input match {
      case "y" => Valid(true)
      case "n" => Valid(false)
      case _   => Validated.invalid(DUIParseError(input))
    }

}
