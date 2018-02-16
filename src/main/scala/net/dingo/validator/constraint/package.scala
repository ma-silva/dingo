package net.dingo.validator

package object constraint {
  implicit class ConstraintOperator[T<:Any](val v: T) extends AnyVal {
    def equalsTo(p: T) = Rules.equalsTo(p).isValid(v)
  }

  implicit class NumConstraintOperator[T<:Any](val v: T)(implicit n: Numeric[T]) {
    def gt(p: T) = Rules.gt(p).isValid(v)

    def gteq(p: T) = Rules.gteq(p).isValid(v)

    def lt(p: T) = Rules.lt(p).isValid(v)

    def lteq(p: T) = Rules.lteq(p).isValid(v)
  }

  implicit class IterConstraintOperator[T<:Iterable[Any]](val v: T) extends AnyVal {
    def size(p: Int) = Rules.size(p).isValid(v)

    def maximum(p: Int) = Rules.maximum(p).isValid(v)

    def minimum(p: Int) = Rules.minimum(p).isValid(v)
  }
}