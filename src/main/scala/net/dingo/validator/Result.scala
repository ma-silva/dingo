package net.dingo.validator

import net.dingo.validator.util.Props

import scala.util.{Failure, Success, Try}

trait Result[+T] extends Any{
  def get: T
}

case object Valid extends Result[Try[Boolean]]{
  override def get: Try[Boolean] = Success(true)
}

class Invalid[T](v: T, e: ResultError) extends Result[Try[Exception]]{
  override def get: Try[Exception] = Failure(throw new IllegalArgumentException(Props.readErr(v, e)))
}

final case class ResultError(errorKey: String, param: Any = "")

trait ResultMessages[T] extends Any{
  def err: ResultError

  def errMsg(msg: String): Rule[T]
}