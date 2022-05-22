package lectures.week2oop



object ClassesOOP extends App {


  val student = new Student ( id = 0, name = "Bob")
  println(student.name)
  println(student.uni)
  println(student.getId(name=" kek", id = 1))
  println(student.getId)

  val noStudent = new Student1()
  val newStudent = new Student1("Will")

  val instructor = new Instructor(324, "Nikita", "LISOVIK")
  println(instructor.fullName())

  val course = new Course(courseID = 0, title = "Math", releaseYear= "1992", instructor = instructor)
  println(course.getID())
  println(course.isTaughtBy(instructor.id))
  println(course.copyCourse("1998"))
}

class Student(id: Int = 0, val name: String) {


  val uni = "University"

//  При создании экземпляра класса автоматически исполняются все конструкции, описанные внутри класса.
  println("Student class")

//  Методы класса
//  Для вызова метода также применяется точечная нотация.
//  Ключевой момент, который вам стоит запомнить - это то, как использование ключевого слова this влияет на результат программы.
  //  Рассмотрим действие this на примере getId - внимание на результат:
  def getId(name: String, id: Int): String = s"${this.name} has ID ${this.id} and $name has ID $id"
//this позволяет компилятору различать параметры класса и параметры метода класса.


//  Перегрузка метода(Overloading)
  //Позволяет иметь функции с одинаковым названием.
  //
  //Единственное условие - чтобы набор аргументов  и(или) их тип был разный, чтобы компилятор мог понять, вызов какой именнно функции вам требуется.

  def getId: String = s"here is $name's ID $id'"
}
//Перегруженные конструкторы
//Класс может иметь несколько конструкторов. Это достигается за счет использования def this.
// Допустим, нам нужен конструктор, который бы по умолчанию использовал 0 в качестве значения id:


class Student1(id: Int, val name: String) {

  def this(name: String) = this(0, name)
  def this() = this(0, "NoName")
}



class Instructor(val id: Int, val name: String, val surname: String) {
  def word_correction(word: String) : String = word.take(1).toUpperCase() + word.substring(1, word.length).toLowerCase()
  def fullName() : String = s"${word_correction(name)} ${word_correction(surname)}"
}

class Course(val courseID: Int, val title: String, releaseYear: String, val instructor: Instructor ) {
  def getID() : String = courseID.toString + instructor.id.toString
  def isTaughtBy(instructor: Int) : Boolean = instructor == this.instructor.id

  def copyCourse(newReleaseYear: String) = new Course(courseID,title,newReleaseYear,instructor )




}
