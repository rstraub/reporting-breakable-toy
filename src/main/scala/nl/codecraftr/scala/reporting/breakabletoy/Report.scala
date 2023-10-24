package nl.codecraftr.scala.reporting.breakabletoy

import cats.Show
import cats.data.NonEmptyList

case class Report(errors: NonEmptyList[ParsingError])

object Report {
  implicit val showReport: Show[Report] = Show.show { report =>
    val errors =
      report.errors.map(error => s"- ${error.message}").toList.mkString("\n")
    s""" |Encountered errors during parsing:
        |$errors""".stripMargin
  }
}
