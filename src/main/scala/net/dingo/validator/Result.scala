package net.dingo.validator

import scala.util.{Failure, Success, Try}

trait Result[+T] extends Any{
  def get: T
}

case object Valid extends Result[Try[Boolean]]{
  override def get: Try[Boolean] = Success(true)
}

case class Invalid(message: String) extends Result[Try[Exception]]{
  override def get: Try[Exception] = Failure(throw new IllegalArgumentException(message))
}

trait ResultMessages[T] extends Any{
  def err: String
  def errMsg(msg: String): Rule[T]
}