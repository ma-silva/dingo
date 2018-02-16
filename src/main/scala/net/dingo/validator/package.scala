package net.dingo

package object validator {
  implicit class ValidatorOperator[T<:Any](val property: T) extends AnyVal {
    def ?(validator: Rule[T]*): Set[Result[Any]] = {
      validator.map(_.verify(property)).toSet
    }
  }

  implicit class IterValidatorOperator(val property: Iterable[Any]) extends AnyVal {
    def ?(validator: Rule[Iterable[Any]]*): Set[Result[Any]] = {
      validator.map(_.verify(property)).toSet
    }
  }
}
