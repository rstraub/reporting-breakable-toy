package nl.codecraftr.scala.kata

import cats.data.NonEmptyList
import cats.data.Validated.Invalid
import nl.codecraftr.scala.kata.Parser.InvalidNumberError
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ReportingSpec extends AnyFlatSpec with Matchers {
  it should "report all errors in a readable fashion" in {
    val result = Report(NonEmptyList.of(InvalidNumberError("c"), InvalidNumberError("a")))

    result shouldBe """ |Errors:
                       |- c
                       |- a""".stripMargin
  }
}
