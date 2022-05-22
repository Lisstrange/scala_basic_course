package lectures.week2oop

object CaseClasses extends App {
//                                                        Классы образцы (Case classes)
  //Мы, может, медленно, зато верно подбираемся к функциональному программированию.
  // Чтобы завершить эту неделю и закрыть тему классов, но в то же время немного приоткрыть, что же вас ждет на следующей неделе - рассмотрим классы образцы.
  //
  //
  //
  //Одной из основных идей функционального программирования является то, что мы стараемся разделить структуры данных и операции над ними.
  // Т.е. все функции обычно сбрасываются в трейты и объекты, но никак не в обычные классы (их вообще желательно избегать,
  // если того позволяет бизнес-логика вашего приложения, прибегая к case классам).
  //
  //Однако существует набор методов, которые всегда придутся кстати для любого класса.
  // Именно доступ к таким методам и дают классы образцы, избавляя вас от необходимости имплементировать эти методы вручную.
  //
  //
  //
  //Объявить класс образец очень легко. Надо всего лишь дописать ключевое слово case.
  //
    case class Person(name: String, occupation: String)
  //
  //
  //Далее опишем, какие преимущества нам дает использование case классов (единственное, что не рассмотрим, это преимущество,
  // связанное с шаблонами - но не волнуйтесь, в следующем модуле все будет )


//                                  Доступен метод copy
  //Метод позволяет как полностью скопировать экземпляр класса, так и скопировать с измененными аргументами конструктора.

    case class Person1(name: String, occupation: String)

    val bob = Person1("Bob", "Developer")

    val anotherBob = bob.copy()
    println(bob) // Person(Bob,Developer)
    println(anotherBob) // Person(Bob,Developer)

    val bobsTwin = bob.copy(name = "John")
    println(bobsTwin) // Person(John,Developer)


//                                        Любой case класс имеет объект-компаньон
  //В таком объекте-компаньоне всегда присутствует метод apply. Которым можно воспользоваться и сделать так:
  //
    case class Person2(name: String, occupation: String)
    val alice = Person2("Alice", "Engineer") // метод apply в действии
  //Т.е. мы создали  экземпляр класса без использования ключевого слова new.
}


object Task1_case_class extends App {


    case class Course(title: String, instructor: String)

    object Course {
        def apply(instructor: String): Course = Course("AdvancedScala", instructor)
    }

    val scalaCourse = Course("Scala", "Bob")



    val course = Course("Scala", "Bob") // Вроде ок

//    val course = scalaCourse.copy("AdvancedScala")
//
//    val course = Course("Alice")
//
//    val course = new Course("Scala")
//
//    val course = scalaCourse.copy()
//
//    val course = new Course("Scala", "Bob")
    println(course)
    println(course == scalaCourse)

}
