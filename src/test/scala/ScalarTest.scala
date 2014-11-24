import org.scalatest._

class ScalarSpec extends FlatSpec with Matchers {

  "A Scalar" should "display a real number without imaginary info" in {
    val scalar1 = Scalar(1, 0)
    val scalarDouble = Scalar(1.0, 0)
    val scalarDouble2 = Scalar(3.2, 0)
    val double = Scalar(1.0)
    scalar1.toString should equal ("1")
    double.toString should equal("1.0")
    scalarDouble.toString should equal ("1.0")
    scalarDouble2.toString should equal ("3.2")
  }

  it should "display complex numbers correctly" in {
    val scalar1 = Scalar(1, 3)
    val scalar2 = Scalar(1.0, 3)
    val scalar3 = Scalar(1, 3.0)
    val scalar4 = Scalar(0, 3)
    val scalar5 = Scalar(0, 3.5)
    scalar1.toString should equal ("1+3i")
    scalar2.toString should equal ("1.0+3.0i")
    scalar3.toString should equal ("1.0+3.0i")
    scalar4.toString should equal ("3i")
    scalar5.toString should equal ("3.5i")
  }

  it should "add numbers correctly" in {
    val scalar1 = Scalar(1, 0)
    val scalar2 = Scalar(-2, 0)
    val scalar3 = Scalar(1, 3)
    val scalar4 = Scalar(-2, 3)
    scalar1.plus(scalar2) should equal (Scalar(-1, 0))
    scalar1.plus(scalar3) should equal (Scalar(2, 3))
    scalar2.plus(scalar3) should equal (Scalar(-1, 3))
    scalar3.plus(scalar4) should equal (Scalar(-1, 6))
    scalar4 + scalar2 should equal (Scalar(-4, 3))
  }

  it should "play nicely with Rational[T]" in {
    val scalar1 = Scalar(Rational(22/7), 3)
    val scalar2 = Scalar(1, 0)
  }
}
