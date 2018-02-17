package net.dingo.validator

import net.dingo.validator.util.Props

import scala.util.{Failure, Success, Try}

trait Result[+T]{
  def get: T
}

case object Valid extends Result[Try[Boolean]]{
  override def get: Try[Boolean] = Success(true)
}

case class Invalid(re: ResultError) extends Result[Try[Exception]]{
  override def get: Try[Exception] = Failure(throw new IllegalArgumentException(Props.readErr(re)))
}

final case class ResultError(key: String, value: Any, param: Any)

trait ResultMessages[T] extends Any{
  def err: String

  def param: Any = ""

  def errMsg(msg: String): Rule[T]
}