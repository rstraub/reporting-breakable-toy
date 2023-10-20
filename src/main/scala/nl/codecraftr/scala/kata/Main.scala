package nl.codecraftr.scala.kata

import nl.codecraftr.scala.kata.Parser.parse

object Main extends App {
  parse(args: _*)
    .leftMap(Report)
    .leftMap(println)
    .foreach { results: List[Int] =>
      results.foreach(println)
    }
}
