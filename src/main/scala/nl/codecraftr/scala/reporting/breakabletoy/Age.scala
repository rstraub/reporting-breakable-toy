package nl.codecraftr.scala.reporting.breakabletoy

import cats.implicits._
import nl.codecraftr.scala.reporting.breakabletoy.ParsingError.{
  InvalidAgeError,
  NotAllowedToDriveError
}

import scala.util.Try

case class Age(value: Int) {
  require(value >= 0, "Age cannot be negative")
}

object Age {
  def from(number: Int): Either[InvalidAgeError, Age] =
    Try(Age(number)).toEither.leftMap(_ => InvalidAgeError(number))
}

case class DrivingAge(age: Age) {
  require(age.value >= 18, "Driving age cannot be less than 18")
}

// TODO what if we make the implementation a private class and define DrivingAge as a trait
object DrivingAge {
  def from(age: Age): Either[NotAllowedToDriveError, DrivingAge] = Try(
    DrivingAge(age)
  ).toEither.leftMap(_ => NotAllowedToDriveError(age))
}
