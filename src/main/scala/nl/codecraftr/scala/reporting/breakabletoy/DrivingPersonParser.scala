package nl.codecraftr.scala.reporting.breakabletoy

import cats.data.ValidatedNec
import cats.implicits._

object DrivingPersonParser {
    def parse(age: String, dui: String): ValidatedNec[ParsingError, DrivingPerson] = {
        (AgeParser.parse(age),
            DUIParser.parse(dui)).mapN(DrivingPerson)
    }
}
