package nl.codecraftr.scala.kata

import cats.Show
import cats.data.NonEmptyList
import nl.codecraftr.scala.kata.Parser.ParsingError

case class Report(errors: NonEmptyList[ParsingError])

object Report {
  implicit val showReport: Show[Report] = Show.show { report =>
    val errors =
      report.errors.map(error => s"- ${error.message}").toList.mkString("\n")
    s""" |Encountered errors during parsing:
        |$errors""".stripMargin
  }
}
