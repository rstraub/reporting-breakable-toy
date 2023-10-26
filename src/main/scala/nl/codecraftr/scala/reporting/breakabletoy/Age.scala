package nl.codecraftr.scala.reporting.breakabletoy

/*
 * TODO: what about using Type Classes to indicate validated values
 *  This could be combined with types to reduce the amount of boilerplate and duplication
 */
// TODO: using inheritance
case class Age(value: Int) extends AnyVal
case class DrivingAge(value: Int) extends AnyVal
