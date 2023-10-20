package nl.codecraftr.scala.kata

import nl.codecraftr.scala.kata.Parser.parse

object Main extends App {
  parse(args: _*)
    .foreach { results: List[Int] =>
      results.foreach(println)
    }
}
