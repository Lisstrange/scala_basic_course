package lectures.week1basics

object StringOperations extends App {
  //  val aString: String = "Hello, world!"
  var aString = "Scala course"

  println(aString.take(5).toUpperCase())
  println(aString.length) // выводит 13
  println(aString.charAt(1)) // выводит e
  println(aString.substring(0, 2)) // выводит He
  println(aString.split(" ").toList) // выводит List(Hello,, world!)
  println(aString.startsWith("He")) // выводит true
  println(aString.replace("!", ".")) // выводит Hello, world.
  println(aString.toLowerCase) // выводит hello, world!
  println(aString.toUpperCase) // выводит HELLO, WORLD!
  println("abcd".reverse) // выводит dcba
  println("abcd".take(2)) // выводит ab


//Код для пребразования строки в число:
  val aNumber = "42".toInt
  println(aNumber) // выводит 42
  println(aNumber.getClass) // выводит int
//Преобразование числа в строку:
  aString = 42.toString

//Рассмотрим парочку кейсов:
  println('1' +: "42" :+ '3') // выводит 1423
  println('a' +: "bc" :+ 'd') // abcd
  println("a" ++ "bc" ++ "d") // abcd

//Хотя теперь мы забегаем очень сильно вперед, но интересно посмотреть, как операторы работают в отношении коллекций:
  println(1 +: List(2, 3)) // List(1, 2, 3)
  println(List(1, 2) ++ List(3, 4)) // List(1, 2, 3, 4)
  println(List(1, 2) +: List(3, 4)) //List(List(1, 2), 3, 4)


//Интерполяция строк
//s-интерполятор - используется для подставления значения переменной типа String в строку.
  var name = "John"
  println(s"Hello, $name") // выводит Hello, John
//Обратите внимание на использование $ в коде. Это гарантирует, что name будет интерпретировано как переменная,
//значение которой требуется подставить в строку.
//Если необходимо вставить выражение, то это выражение указываем в фигурных скобках:
  name = "John"
  val surname = "Smith"
  println(s"Hello, ${name + surname}") // выводит Hello, JohnSmith
//      2. raw-интерполятор
//Главное, что вам нужно о нем знать, он не преобразовывает сырую строку и необрабатывает /n /t и т.п



  val test_name: String = "NIKITA"

  println(test_name.length)
  println(test_name.take(1).toUpperCase() + test_name.substring(1,test_name.length).toLowerCase() )
}
