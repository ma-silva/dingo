package net.dingo.validator.constraint

import net.dingo.validator.{Rule}

object Rules {

  final case class equalsTo[T](c: T) extends AnyVal with Rule[T]{
    override def isValid(v: T): Boolean = v.equals(c)

    override def err: String = "msgErr.equalsTo"

    override def param: Any = c
  }

  final case class nonEmpty[T](p: T = null) extends AnyVal with Rule[T] {
    override def isValid(v: T = p): Boolean = isEmpty(v)

    private[this] def isEmpty(value: T) = value match {
      case str if str.isInstanceOf[String] => value.asInstanceOf[String] != null && !value.asInstanceOf[String].isEmpty
      case iter if iter.isInstanceOf[Iterable[Any]] => value.asInstanceOf[Iterable[Any]].nonEmpty
      case _ => false
    }

    override def err: String = "msgErr.nonEmpty"
  }

  final case class alphabetic(p: String = "") extends AnyVal with Rule[String] {
    override def isValid(v: String = p): Boolean = v.matches("^[A-z]+$")

    override def err: String = "msgErr.alphabetic"
  }

  final case class email(p: String = "") extends AnyVal with Rule[String] {
    override def isValid(v: String = p): Boolean = v. matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")

    override def err: String = "msgErr.email"
  }

  final case class startsWith(c: String) extends AnyVal with Rule[String] {
    override def isValid(v: String): Boolean = v.startsWith(c)

    override def err: String = "msgErr.email"

    override def param: Any = c
  }

  final case class endsWith(c: String) extends AnyVal with Rule[String] {
    override def isValid(v: String): Boolean = v.endsWith(c)

    override def err: String = "msgErr.endsWith"

    override def param: Any = c
  }

  final case class matches(r: String) extends AnyVal with Rule[String] {
    override def isValid(v: String): Boolean = v.matches(r)

    override def err: String = "msgErr.matches"

    override def param: Any = r
  }

  final case class TRUE(p: Boolean = true) extends AnyVal with Rule[Boolean] {
    override def isValid(v: Boolean = p): Boolean = v

    override def err: String = "msgErr.true"
  }

  final case class FALSE(p: Boolean = false) extends AnyVal with Rule[Boolean] {
    override def isValid(v: Boolean = p): Boolean = !v

    override def err: String = "msgErr.false"
  }

  final case class gt[T](p: T)(implicit n: Numeric[T]) extends Rule[T]{
    override def isValid(v: T): Boolean = n.gt(v,p)

    override def err: String = "msgErr.gt"

    override def param: Any = p
  }

  final case class gteq[T](p: T)(implicit n: Numeric[T]) extends Rule[T]{
    override def isValid(v: T): Boolean = n.gteq(v,p)

    override def err: String = "msgErr.gteq"

    override def param: Any = p
  }

  final case class lt[T](p: T)(implicit n: Numeric[T]) extends Rule[T]{
    override def isValid(v: T): Boolean = n.lt(v,p)

    override def err: String = "msgErr.lt"

    override def param: Any = p
  }

  final case class lteq[T](p: T)(implicit n: Numeric[T]) extends Rule[T]{
    override def isValid(v: T): Boolean = n.lteq(v,p)

    override def err: String = "msgErr.lteq"

    override def param: Any = p
  }

  final case class even(p: Int = 0) extends AnyVal with Rule[Int] {
    override def isValid(v: Int = p): Boolean = isEven(v)

    private[this] def isEven(self: Int) = self % 2 == 0

    override def err: String = "msgErr.even"
  }

  final case class odd(p: Int = 0) extends AnyVal with Rule[Int] {
    override def isValid(v: Int = p): Boolean = isOdd(v)

    private[this] def isOdd(self: Int) = self % 2 != 0

    override def err: String =  "msgErr.odd"
  }

  final case class size(s: Int) extends AnyVal with Rule[Iterable[Any]] {
    override def isValid(v: Iterable[Any]): Boolean = v.size == s

    override def err: String = "msgErr.size"

    override def param: Any = s
  }

  final case class maximum(s: Int) extends AnyVal with Rule[Iterable[Any]] {
    override def isValid(v: Iterable[Any]): Boolean = v.size >= s

    override def err: String = "msgErr.max"

    override def param: Any = s
  }

  final case class minimum(s: Int) extends AnyVal with Rule[Iterable[Any]] {
    override def isValid(v: Iterable[Any]): Boolean = v.size <= s

    override def err: String = "msgErr.min"

    override def param: Any = s
  }
}