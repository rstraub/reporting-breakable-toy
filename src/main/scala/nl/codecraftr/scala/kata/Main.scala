package nl.codecraftr.scala.kata

import cats.implicits._
import nl.codecraftr.scala.kata.Parser.parse

object Main extends App {
  def run(args: List[String]): List[DrivingAge] = {
    parse(args: _*)
      .leftMap(Report.apply)
      .leftMap(_.show)
      .leftMap(println)
      .getOrElse(List.empty)
  }

  run(args.toList)
    .foreach(println)
}
