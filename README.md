# Dingo
A scala validator that supports value classes mechanism.
Currently no artifact yet.


### Example
```scala
case class Student(name: String, email: String, code: String, age:Int, grade: Int, enrolled: Boolean) extends RichValidator
val student = new Student("Klarenz","klarenz@mail.com","st1", 18, 99, true)

println(EmailText(student.email).isValid) // Prints true.

implicit val constraint = RichConstraint[Student](student)(s =>
  s.name ? (nonEmpty(), alphabetic(), matches("^[A-z]+$")) ++
  s.code ? (startsWith("s")) ++
  s.email ? (nonEmpty(), email()) ++
  s.age ? gt(1) ++
  s.enrolled ? TRUE()
)

student.validate //Calling validate requires implicit RichConstraint and throws exception if invalid.
student.isValid //Calling validate requires implicit RichConstraint and Return true if valid.
```

### Custom error messages
```scala
student.name ? equalsTo("Mark").errMsg("Error.. ") // Overrides the default exception messages.
```

## License
dingo is Open Source and available under the Apache 2 License.
