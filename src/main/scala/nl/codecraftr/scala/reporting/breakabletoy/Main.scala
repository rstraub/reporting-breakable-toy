package nl.codecraftr.scala.reporting.breakabletoy

import cats.data.Validated
import cats.implicits._
import nl.codecraftr.scala.reporting.breakabletoy.AgeParser.parse

object Main extends App {
  def run(args: List[String]): Validated[Report, List[DrivingAge]] = {
    parse(args: _*)
      .leftMap(Report.apply)
  }

  run(args.toList)
    .leftMap(_.show)
    .leftMap(println)
    .foreach(println)
}
