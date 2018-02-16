package net.dingo.validator.constraint

import net.dingo.validator.{Result}

trait Constraint {
  def validate: Unit
  def isValid: Boolean
}

case class RichConstraint[T](domain: T)(mapping: T => Set[Result[Any]]) extends Constraint{
  override def validate: Unit = mapping(domain).map(_.get)

  override def isValid: Boolean = {
    val results = mapping(domain)
    results.forall(_ == results.head)
  }
}