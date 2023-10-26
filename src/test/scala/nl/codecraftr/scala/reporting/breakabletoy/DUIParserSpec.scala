package nl.codecraftr.scala.reporting.breakabletoy

import cats.data.Validated.{Invalid, Valid}
import nl.codecraftr.scala.reporting.breakabletoy.ParsingError.DUIParseError
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DUIParserSpec  extends AnyFlatSpec with Matchers {
    it should "return true given 'y'" in {
        DUIParser.parse("y") shouldBe Valid(true)
    }

    it should "return false given 'n'" in {
        DUIParser.parse("n") shouldBe Valid(false)
    }
    it should "return parse error given anything else" in {
        DUIParser.parse("x") shouldBe Invalid(DUIParseError("x"))
    }
}
