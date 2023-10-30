package nl.codecraftr.scala.reporting.breakabletoy

import cats.data.Validated
import cats.implicits._

object Main extends App {
  // a y -1 n 17 x 20 n
  // [[1 y] [2 n] [3 x]]
  def run(args: List[String]): Validated[Report, List[DrivingPerson]] = {
    args
      .grouped(2)
      .toList
      .map(dp => DrivingPersonParser.parse(dp.head, dp.last))
      .sequence
      .leftMap(Report(_))
  }

  run(args.toList)
    .leftMap(_.show)
    .leftMap(println)
    .foreach(println)
}
