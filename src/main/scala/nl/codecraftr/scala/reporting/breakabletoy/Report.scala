package nl.codecraftr.scala.reporting.breakabletoy

import cats.Show
import cats.data.NonEmptyChain
import cats.implicits._

case class Report(errors: NonEmptyChain[ParsingError])

object Report {
  implicit val showReport: Show[Report] = Show.show { report =>
    val errors =
      report.errors.map(error => s"- ${error.message}").toList.mkString("\n")
    s""" |Encountered errors during parsing:
        |$errors""".stripMargin
  }
}
