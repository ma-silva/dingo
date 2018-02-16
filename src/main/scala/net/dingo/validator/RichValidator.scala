package net.dingo.validator

import net.dingo.validator.constraint.Constraint

trait RichValidator {
  @throws[IllegalArgumentException]("If invalid.")
  def validate[T<:Constraint](implicit constraint: T): Unit = constraint.validate

  def isValid[T<:Constraint](implicit constraint: T): Boolean = constraint.isValid
}