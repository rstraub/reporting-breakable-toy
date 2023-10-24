package nl.codecraftr.scala.reporting.breakabletoy

import cats.data.NonEmptyChain
import cats.implicits.toShow
import nl.codecraftr.scala.reporting.breakabletoy.ParsingError._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ReportingSpec extends AnyFlatSpec with Matchers {
  it should "report all errors in a readable fashion" in {
    val result =
      Report(
        NonEmptyChain.of(InvalidNumberError("c"), InvalidNumberError("a"))
      ).show

    result shouldBe """ |Encountered errors during parsing:
                       |- Invalid number: 'c'
                       |- Invalid number: 'a'""".stripMargin
  }
}
