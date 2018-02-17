package net.dingo.validator

trait Validator[T] extends Any{
  def isValid(v: T): Boolean
}

trait Rule[T] extends Any with Validator[T] with ResultMessages[T] {
  override def errMsg(msg: String): Rule[T] = new CustomErrorRule[T](this) {
    override def err: String = msg
  }

  def verify(v: T): Result[Any] = if (isValid(v))
    Valid else Invalid(ResultError(err, v, param))
}

sealed abstract class CustomErrorRule[T](that: Rule[T])extends Rule[T] {
  override def isValid(v: T): Boolean = that.isValid(v)

  override def param = that.param
}