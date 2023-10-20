package nl.codecraftr.scala.kata

import cats.data.Validated._
import cats.data.ValidatedNel
import cats.implicits._

import scala.util.Try

object Parser {
  case class ParsingError(input: String)
  def parse(inputs: String*): ValidatedNel[ParsingError, List[Int]] =
    inputs.toList
      .map(parseInput)
      .traverse(_.toValidatedNel)

  private def parseInput(input: String) = Try(
    input.toInt
  ).toEither.leftMap(_ => ParsingError(input))

}
