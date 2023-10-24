package nl.codecraftr.scala.reporting.breakabletoy

sealed trait ParsingError {
  def message: String
}

object ParsingError {
  final case class InvalidNumberError(input: String) extends ParsingError {
    override def message: String = s"Invalid number: '$input'"
  }

  final case class InvalidAgeError(age: Int) extends ParsingError {
    override def message: String = s"Age must be a positive number: '$age'"
  }

  final case class NotAllowedToDriveError(age: Age) extends ParsingError {
    override def message: String =
      s"Age must be at least 18 to drive: '${age.value}'"
  }
}
