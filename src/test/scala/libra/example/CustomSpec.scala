package libra.example

import org.scalatest._

import libra._
import libra.ops.base.Show
import shapeless._
import spire.algebra._
import spire.implicits._
import spire.math._

class CustomSpec extends WordSpec with Matchers {

  import RomanLengths._

  "libra" should {
    "show custom units" in {
      assert(42.pes3.show === "42 pes^3 [R^3]")
    }

    "divide custom units" in {
      assert(10.pes3 /~ 5.pes2 === 2.pes)
    }
  }
}

object RomanLengths {

  type RomanLength

  implicit def romanLengthShow: Show[RomanLength] = Show[RomanLength]("R")

  /** Roman foot */
  trait Pes extends UnitOfMeasure[RomanLength]

  /** Roman palm */
  trait Palmus extends UnitOfMeasure[RomanLength]

  implicit def pesShow: Show[Pes] = Show[Pes]("pes")
  implicit def palmusShow: Show[Palmus] = Show[Palmus]("palmus")

  val wOne = Witness(1)
  val wTwo = Witness(2)
  val wThree = Witness(3)

  type LengthSquared[A, L <: UnitOfMeasure[RomanLength]] = Quantity[A, Term[RomanLength, L, Fraction[wTwo.T, wOne.T]] :: HNil]
  type LengthCubed[A, L <: UnitOfMeasure[RomanLength]] = Quantity[A, Term[RomanLength, L, Fraction[wThree.T, wOne.T]] :: HNil]

  implicit final class RomanQuantityOps[A](a: A) {
    def pes: QuantityOf[A, RomanLength, Pes] = Quantity(a)
    def palmus: QuantityOf[A, RomanLength, Palmus] = Quantity(a)

    def pes2: LengthSquared[A, Pes] = Quantity(a)
    def palmus2: LengthSquared[A, Palmus] = Quantity(a)

    def pes3: LengthCubed[A, Pes] = Quantity(a)
    def palmus3: LengthSquared[A, Palmus] = Quantity(a)
  }
}
