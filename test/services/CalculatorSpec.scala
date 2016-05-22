package services

import org.scalatest.{FlatSpec, Matchers}


class CalculatorSpec extends FlatSpec with Matchers {
  "calculator - sum" should "summarize numbers" in {
    new Calculator().sum(List(1, 3, 5)) should equal(9)
  }
}
