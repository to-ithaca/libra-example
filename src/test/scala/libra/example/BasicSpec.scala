package libra.example

import org.scalatest._

import libra.si._
import spire.implicits._

class BasicSpec extends WordSpec with Matchers {

  "libra" should {
    "add quantities with units provided by libra" in {
      assert((1.m + 2.m) === 3.m)
    }

    "multiply quantities with units provided by libra" in {
      assert((2.m * 3.m) === (6.m * 1.m))
    }

    "show quantities with units provided by libra" in {
      assert(2.m.show === "2 m [L]")
    }
  }
}
