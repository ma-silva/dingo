package net.dingo.validator.util

import java.io.InputStream
import java.util.Properties

import net.dingo.validator.ResultError

sealed trait PropertiesReader {
  val file: String

  lazy val prop = new Properties()

  lazy val stream: InputStream = getClass.getResourceAsStream(file)

  def readErr(e: ResultError): String
}

sealed trait Parser {
  private val value = "{0}"

  private val valueToCompare = "{1}"

  protected def parse(s: String, v1: Any, v2: Any): String = s.replace(value, v1.toString)
    .replace(valueToCompare, v2.toString)
}

object Props extends PropertiesReader with Parser {
  override val file: String = "/default.properties"

  {
    prop.load(stream)
  }

  override def readErr(e: ResultError): String = parse(
    prop.getProperty(e.key, e.key),
    e.value,
    e.param)
}