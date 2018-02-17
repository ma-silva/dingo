package net.dingo.validator

import net.dingo.validator.constraint.Rules.{alphabetic, matches, size, _}
import net.dingo.validator.constraint._
import org.scalatest.FlatSpec

class ValidationSpec extends FlatSpec{
  final case class Student(name: String, email: String, code: String, age:Int, grade: Int, enrolled: Boolean) extends RichValidator
  val student = new Student("Klarenz","klarenz@mail.com","st1", 18, 99, true)
  val student2 = new Student("Claire","claire@mail.com","st2", 19, 80, false)
  val students = List(student, student2)
  val students2 = List(student)

  "Student data" should "be valid" in {
    println((student.name ? equalsTo("Klarenzs").errMsg("{0} is not equal to {1}")).map(_.get))
    println((student.name ? equalsTo("Klarenz")).map(_.get))

    assert((student.name ? equalsTo("Klarenz")).contains(Valid))
    assert(student.name equalsTo "Klarenz")

    assert((student.name ? nonEmpty()).contains(Valid))
    assert(nonEmpty(student.name).isValid())

    assert((student.name ? alphabetic()).contains(Valid))
    assert(alphabetic(student.name).isValid())

    assert((student.email ? email()).contains(Valid))
    assert(email(student.email).isValid())

    assert((student.name ? startsWith("K")).contains(Valid))
    assert(student.name startsWith "K")

    assert((student.name ? endsWith("z")).contains(Valid))
    assert(student.name endsWith "z")

    assert((student.code ? matches("^[a-zA-Z0-9_]*$")).contains(Valid))
    assert(student.code matches("^[a-zA-Z0-9_]*$"))

    assert((student.enrolled ? TRUE()).contains(Valid))
    assert(TRUE(student.enrolled).isValid())

    assert((student2.enrolled ? FALSE()).contains(Valid))
    assert(FALSE(student2.enrolled).isValid())

    assert((student.age ? gt{1}).contains(Valid))
    assert(student.age gt 1)

    assert((student.age ? gteq(18)).contains(Valid))
    assert(student.age gteq 18)

    assert((student.age ? lt(20)).contains(Valid))
    assert(student.age lt 20)

    assert((student.age ? lteq(18)).contains(Valid))
    assert(student.age lteq 18)

    assert((student.age ? even()).contains(Valid))
    assert(even(student.age).isValid())

    assert((student.grade ? odd()).contains(Valid))
    assert(odd(student.grade).isValid())
  }

  "Student" should "valid" in {
    implicit val constraint = RichConstraint[Student](student)(s =>
      s.name ? (nonEmpty(), alphabetic(), matches("^[A-z]+$")) ++
      s.code ? (startsWith("s")) ++
      s.email ? (nonEmpty(), email()) ++
      s.age ? gt{1} ++
      s.enrolled ? TRUE()
    )

//    student.validate

    assert(student.isValid)
  }

  "List of Students" should "be valid" in {
    assert((students ? nonEmpty()).contains(Valid))
    assert(nonEmpty(students).isValid())

    assert((students ? size(2)).contains(Valid))
    assert(students size 2)

    assert((students ? maximum(2)).contains(Valid))
    assert(students maximum 2)

    assert((students2 ? minimum{1}).contains(Valid))
    assert(students2 minimum 1)
  }
}
