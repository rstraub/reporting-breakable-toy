package nl.codecraftr.scala.kata

import cats.data.NonEmptyList
import cats.data.Validated.Invalid
import nl.codecraftr.scala.kata.Parser.ParsingError
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ReportingSpec extends AnyFlatSpec with Matchers {
  it should "report all errors in a readable fashion" in {
    val result = Report(NonEmptyList.of(ParsingError("c"), ParsingError("a")))

    result shouldBe """ |Errors:
                       |- c
                       |- a""".stripMargin
  }
}
