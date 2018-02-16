# dingo
A scala validator that is based on value classes mechanism.


### Example
```scala



case class Student(name: String, email: String, code: String, age:Int, grade: Int, enrolled: Boolean) extends RichValidator
val student = new Student("Klarenz","klarenz@mail.com","st1", 18, 99, true)

println(EmailText(student.email).isValid) // Prints true.

implicit val constraint = RichConstraint[Student](student)(s =>
  s.name ? (valid, alphabetic, matches("^[A-z]+$")) ++
  s.code ? (startsWith("s")) ++
  s.email ? (valid, email) ++
  s.enrolled ? TRUE
)

student.validate //Calling validate requires implicit RichConstraint.
```

## License
dingo is Open Source and available under the Apache 2 License.
