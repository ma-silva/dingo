package net.dingo.validator

package object constraint {
  implicit class ConstraintOperator[T<:Any](val v: T) extends AnyVal {
    def equalsTo(p: T): Boolean = Rules.equalsTo(p).isValid(v)
  }

  implicit class NumConstraintOperator[T<:Any](val v: T)(implicit n: Numeric[T]) {
    def gt(p: T): Boolean = Rules.gt(p).isValid(v)

    def gteq(p: T): Boolean = Rules.gteq(p).isValid(v)

    def lt(p: T): Boolean = Rules.lt(p).isValid(v)

    def lteq(p: T): Boolean = Rules.lteq(p).isValid(v)
  }

  implicit class IterConstraintOperator[T<:Iterable[Any]](val v: T) extends AnyVal {
    def size(p: Int): Boolean = Rules.size(p).isValid(v)

    def maximum(p: Int): Boolean = Rules.maximum(p).isValid(v)

    def minimum(p: Int): Boolean = Rules.minimum(p).isValid(v)
  }
}