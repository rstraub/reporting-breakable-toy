package nl.codecraftr.scala.reporting.breakabletoy

import cats.implicits._
import nl.codecraftr.scala.reporting.breakabletoy.ParsingError.{
  InvalidAgeError,
  NotAllowedToDriveError
}

import scala.util.Try

/*
 * TODO: what about using Type Classes to indicate validated values
 *  This could be combined with types to reduce the amount of boilerplate and duplication
 */
// TODO: using inheritance
case class Age(value: Int) {
  require(value >= 0, "Age cannot be negative")
}

object Age {
  def from(number: Int): Either[InvalidAgeError, Age] =
    Try(Age(number)).toEither.leftMap(_ => InvalidAgeError(number))
}

case class DrivingAge(value: Int) extends AnyVal {
  require(value >= 18, "Driving age cannot be less than 18")
}

object DrivingAge {
  def from(age: Age): Either[NotAllowedToDriveError, DrivingAge] = Try(
    DrivingAge(age.value)
  ).toEither.leftMap(_ => NotAllowedToDriveError(age))
}
