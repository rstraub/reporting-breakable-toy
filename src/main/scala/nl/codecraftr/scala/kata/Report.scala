package nl.codecraftr.scala.kata

import cats.data.NonEmptyList
import nl.codecraftr.scala.kata.Parser.ParsingError

case class Report(errors: NonEmptyList[ParsingError]) {}
