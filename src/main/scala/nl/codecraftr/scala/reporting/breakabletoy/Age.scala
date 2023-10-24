package nl.codecraftr.scala.reporting.breakabletoy

/*
 TODO how could we not duplicate the data model between Age -> DrivingAge
 Goal: typesafe way to prove that we are dealing with the right age,
 but not duplicate the `value`
 */
case class Age(value: Int) extends AnyVal
case class DrivingAge(value: Int) extends AnyVal
