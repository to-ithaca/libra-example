package libra.example

import org.scalatest._

import libra._
import libra.ops.base.Show
import shapeless._
import spire.algebra._
import spire.implicits._
import spire.math._


/**
  * This is a demonstration of how to create custom units with libra
  *
  * This creates Ancient Roman units of measure for lengths.
  */
object CustomSpec {

  /**
    * A type for the Roman Length dimension.
    *
    * This is a demonstration of how to declare a custom dimension.
    * A custom length dimension is actually unnecessary, if not problematic, here as
    * libra has a [[libra.si.Length]].  Using the length declared inside libra allows
    * conversions to other libra length units, e.g. metres.
    * */
  type RomanLength


  /**
    * Unit of measure for a Roman foot.
    *
    * Note that this is linked to the [[RomanLength]].
    */
  trait Pes extends UnitOfMeasure[RomanLength]

  /**
    * Unit of measure for a Roman palm.
    *
    * Note that this is linked to the [[RomanLength]]
    */
  trait Palmus extends UnitOfMeasure[RomanLength]

  /** Show typeclasses allow quantities to have a String representation */
  implicit def romanLengthShow: Show[RomanLength] = Show[RomanLength]("R")
  implicit def pesShow: Show[Pes] = Show[Pes]("pes")
  implicit def palmusShow: Show[Palmus] = Show[Palmus]("palmus")

  /**
    * Shapeless witnesses for integer literals.
    *
    * These are only necessary when not using Typelevel Scala, as Typelevel Scala
    * supports literal types in type position.
    * 
    */
  val wOne = Witness(1)
  val wTwo = Witness(2)
  val wThree = Witness(3)

  /**
    * Type aliases to length squared and cubed quantities.
    *
    * The quantity types contain length terms with fractional powers.
    *
    * @tparam A  The numeric type to base the unit on (e.g. [[Double]])
    * @tparam L  The unit of measure.  It must be a Roman Length
    */
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


class CustomSpec extends WordSpec with Matchers {

  import CustomSpec._

  "libra" should {
    "show custom units" in {
      assert(42.pes3.show === "42 pes^3 [R^3]")
    }

    "divide custom units" in {
      assert(10.pes3 /~ 5.pes2 === 2.pes)
    }
  }
}
