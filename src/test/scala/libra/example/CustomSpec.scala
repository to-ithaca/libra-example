package libra.example

import org.scalatest._

import libra._
import libra.ops.base.Show
import shapeless._
import spire.algebra._
import spire.implicits._
import spire.math._

class CustomSpec extends WordSpec with Matchers {

  import CustomSpec._

  "libra" should {
    "facilitate custom units using witnesses" in {
      assert(3.gallons.show === "693 in^3 [L^3]")
    }
  }
}

object CustomSpec {

  type CustomLength
  implicit def lengthShow: Show[CustomLength] = Show[CustomLength]("L")

  trait Inch extends UnitOfMeasure[CustomLength]

  implicit def inchShow: Show[Inch] = Show[Inch]("in")

  val wOne = Witness(1)
  val wThree = Witness(3)

  type InchesCubed[A] = Quantity[A, Term[CustomLength, Inch, Fraction[wThree.T, wOne.T]] :: HNil]

  implicit final class QuantityGallonOps[A](a: A) {
    def gallons(implicit M: MultiplicativeSemigroup[A], C: ConvertableTo[A]): InchesCubed[A] =
      Quantity(a * C.fromInt(231))
  }
}
