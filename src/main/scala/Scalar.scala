import scala.math._
import scala.language.implicitConversions

case class Rational[T <: Integral[T]] (a: T, b: T)(implicit t: Integral[T]) extends Numeric[T] {
  require(b != 0)
  val r = reduce(this).a
  val q = reduce(this).b

  private def gcd(a: T, b: T): T = b match {
    case 0 => a
    case _ => gcd(b, (t.rem(a, b)))
  }

  private def reduce(a: Rational[T]): Rational[T] = {
    val g = gcd(a.q, a.r)
    Rational(t.quot(a.r,g), t.quot(a.q, g))
  }

  implicit def fromInt(x: Int) = Rational(x, 1)

  def plus(a: Rational[T], b: Rational[T]): Rational[T] = 
    reduce(Rational(t.plus(t.times(a.q, b.r), t.times(a.r, b.q)), t.times(a.r, b.r)))

//  def compare(a: T, b: T) = 

  override def toString = {
    if(q == 0) "0"
    else if((t.lt(q, t.zero) && t.lt(r, t.zero)) || (t.gt(q, t.zero) && t.gt(r, t.zero)))
      t.abs(q).toString + "/" + t.abs(r).toString
    else
      "-" + q.toString + "/" + r.toString
  }
}



case class Scalar[T] (re: T, im: T = 0)(implicit t: Numeric[T]) {
  override def toString = if(t.equiv(t.zero, im)) this.re.toString
  else {
    if(!t.equiv(t.zero,re)) re.toString + ( 
      if(t.lt(im, t.zero)) "-" + t.negate(im).toString + "i"
      else if(t.gt(im, t.zero)) "+" + im.toString + "i")
    else im.toString + "i"
  }

  def plus(op: Scalar[T]) = Scalar(t.plus(this.re, op.re), t.plus(this.im, op.im))
  
  def +(rhs: Scalar[T]) = plus(rhs)
  


}








