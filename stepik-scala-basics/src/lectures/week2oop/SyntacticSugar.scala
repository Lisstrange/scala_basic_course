package lectures.week2oop

object SyntacticSugar extends App {

//                                          Инфиксная нотация (Infix notation)
//  Подходит только для методов с одним параметром
//  Создадим класс Person, в котором пропишем метод worksAs:

//  class Person(val name: String, occupation: String) {
//    def worksAs(jobName: String): Boolean = jobName == occupation
//  }

//  Далее создадим экземпляр класса Person:

//  val bob = new Person("Bob", "Developer")

//  Если мы захотим для Боба вызвать метод worksAs, то можем это сделать привычным нам способом - через точечную нотацию:
//    println(bob.worksAs("Developer")) // true

//  Но так как worksAs принимает на вход всего один аргумент, то вызов этого метода мы можем переписать в инфиксной нотации:
//    println(bob worksAs "Developer") // true
//  Результат вызова функции от этого нисколько не изменился, но посмотрите, насколько код стал напоминать обычное предложение. А ведь мы всего-то избавились от точки и скобок, обратившись к пробелам.


//                                            Метод apply
//   вот этот материал вам нужно точно запомнить
  //
  //
  //
  //Для начала - добавим apply в класс (то, что метод будет делать - не суть важно, главное сейчас - чтобы apply просто был):
  //
    class Person(val name: String, occupation: String) {
      def worksAs(jobName: String): Boolean = jobName == occupation
      def speaksEnglish: Boolean = true
      def &(person: Person): String = s"${this.name} and ${person.name} are colleagues"
      def unary_! : String = s"$name is not real"

      def apply(): String = s"$name works as a $occupation"
    }


    val bob = new Person("Bob", "Developer")


//  И опять у нас на выбор два способа вызова apply:

    println(bob.apply())
    println(bob()) // используется наболее часто
  //Причем второй способ наиболее популярен. Так что запомните: как только вы видите какой-то объект класса, который подозрительно похож на функцию, знайте - все дело в apply.


}
