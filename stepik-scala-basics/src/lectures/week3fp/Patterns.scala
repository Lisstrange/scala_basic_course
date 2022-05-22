package lectures.week3fp

object Patterns extends App {
                                                      //  Шаблоны (Patterns)
  //Шаблоны применяются тогда, когда для конкретных значений требуется определить свой вариант действия программы (можно провести параллели со switch-case из других языков). Выглядит это так:
  //
    val someVal = 3

    val description = someVal match {
      case 1 => "action 1"
      case 2 => "action 2"
      case 3 => "action three"
      case _ => "default action"
    }

    println(description) // action three
  //Рядом с case мы задаем шаблоны, которые сравниваем со значением переменной someVal. А после => указываем желаемый результат для заданного случая.
  //
  //Символ _ обычно описывает ситуацию, когда совпадений среди шаблонов не найдено т.е. результат по умолчанию.


//  Виды шаблонов
  //В качестве шаблонов можно использовать:
  //
  //константы
    val x: Any = "Scala"
    val constants = x match {
      case 1 => "число"
      case "Scala" => "строка"
      case true => "булевое значение"
    }
  println(constants) // строка

  //Обратите внимание на тип Any. Он потребовался в силу того, что в качестве x в нашем случае может быть использовано и число, и строка, и булевое значение
  //
  //
  //
  //туплы
    val aTuple = (5,2)
    val matchATuple = aTuple match {
      case (1, 1) => "полное совпадение"
      case (something, 2) => s"я нашел $something"
    }

    println(matchATuple) // я нашел 5
  //Вложенные туплы также не являются проблемой:

    val nestedTuple = (1, (2, 3))
    val matchNestedTuple = nestedTuple match {
      case (_, (2, v)) => s"тут есть число $v"
    }

    println(matchNestedTuple) // тут есть число 3


  //списки
    val aStandardList = List(5, 4)
    val listMatching = aStandardList match {
      case List(1, _, _, _) => "список из 4 элементов, где первый элемент равен 1"
      case List(2, _*) => "список произвольной длины, где первый элемент равен 2"
      case List(3, 2, 1) :+ 0 => "список List(3, 2, 1, 0)"
      case 5 :: List(_) => "список, где первым идет число 5 и еще один элемент"
      case _ => "default"
    }


  //типы
    val unknown: Any = List(1, 2)
    val typeMatch = unknown match {
      case list: List[Int] => "list of INTs"
      case _ => "default"
    }

    println(typeMatch) // list of INTs


  //классы-образцы
    case class Person(name: String, age: Int)

    val bob = Person("Bob", 30)
    val greeting = bob match {
      case Person(n, a) if a < 18 => s"I'm $n and I'm under 18"
      case Person(n, a) if n != "name" => s"I'm $n and I am $a years old"
      case _ => "I have no name"
    }

    println(greeting) // I'm Bob and I am 30 years old


//  Привязка имен (Name Binding)
  //Довольно полезная вещь, если требуется как-то обратиться к найденному совпадению.
  //
  //
  //Задается имя совпадения, ставится знак @, после которого прописывается сам шаблон:

    val nameBindingMatch = List(6, 2) match {
      case nonEmptyList@List(1, _, _, _) => s"нашли $nonEmptyList"
      case someList@List(6, _*) => s"нашли список $someList"
    }

    println(nameBindingMatch) // нашли список List(6, 2)


//  Поговорим еще немного о шаблонах. Посмотрите на код:

    val numbers = List(1, 2, 3)
    val numbersMatch = numbers match {
      case listOfStrings: List[String] => "a list of strings"
      case listOfNumbers: List[Int] => "a list of integers"
      case _ => "default case"
    }

    println(numbersMatch) // a list of strings
  //Если код запустить, то на экране увидите a list of strings. И это несмотря на то, что на вход был подан целочисленный список.
  //
  //
  //Причина этому - JVM. Компилятор на самом деле видит код без generic-типов. Т.е. для компилятора код выглядит вот так:

//    val numbersMatch = numbers match {
//      case listOfStrings: List[String] => "a list of strings"
//      case listOfNumbers: List[Int] => "a list of integers"
//      case _ => "default case"
//    }
  //А так как шаблоны проверяются по порядку - один за другим, то первый же шаблон будет считаться подходящим, и на экране будет a list of strings
}

object Task3 extends App {
//  Давайте напишем метод guard, который получает на вход два параметра: data и maxLength:
  //
  //data - это то, что мы скармливаем методу на проверку. Это может быть все, что угодно: число, строка, список, вектор - но что именно, вам заранее не известно
  //maxLength - максимально допустимая длина строки или списка
  //типом возвращаемого значения для guard является String
  //
  //
  //Внутри метода необходимо прописать шаблон, состоящий из пяти case. Результаты проверок на соответствие шаблону следующие:
  //
  //Задан список List допустимой длины
  //Длина списка больше максимально допустимого значения
  //Длина строки не превышает максимально допустимого значения
  //Получена строка недопустимой длины
  //Что это? Это не строка и не список
  //
  //
  //Примечание: напишите только код метода, вызывать его не надо
  //
  //Sample Input:
  //
  //Hello 5
  //Sample Output:
  //
  //Длина строки не превышает максимально допустимого значения

  val test_data : String = "Hello"
  val test_max_lenght : Int = 5
  def guard(data: Any, maxLengt: Int):String = {
    val res = data match {
      case data: List[Any] if data.length <= maxLengt => "Задан список List допустимой длины"
      case data: List[Any] if data.length > maxLengt => "Длина списка больше максимально допустимого значения"
      case data: String  if data.length <= maxLengt => "Длина строки не превышает максимально допустимого значения"
      case data: String  if data.length > maxLengt => "Получена строка недопустимой длины"
      case _=> "Что это? Это не строка и не список"

    }
    res
  }



  println(guard(test_data, test_max_lenght))

}



object Task2 extends App {
//  Регулярные выражения также могут быть использованы в шаблоне. Давайте напишем программу, помогающую получить инициалы человека.
  //
  //
  //
  //Естественно, задача нацелена на отработку шаблонов, так что программа выдает один из следующих возможных результатов:
  //
  //инициалы человека (заглавные буквы имени и фамилии с точкой, т.е. для John Smith результатом будет J. S. - внимание на точки и пробел)
  //сообщение Oops, something is wrong
  //
  //
  //Примечания:
  //
  //в этот раз вы должны полностью написать код (за вас предварительно ничего не написано - вообще ничего, даже object Main), так что в начале не забудьте считать данные, а в конце - распринтить результаты
  //на вход при тестировании мы подаем строку вида Name Surname
  //предусматривать ситуации, когда на вход были переданы имя и фамилия в нижнем регистре - не надо, в данном примере заглавные буквы или нет - не суть важно, главное суметь взять первые буквы имени и фамилии
  //предусматривать попадание специальных символов вроде + - /  * тоже не обязательно
  //то, как вы организуете код - решать вам (для прохождения теста системой важен сам результат), однако если вы все же хотите профессионально подойти к решению поставленной задачи, то рекомендуем использовать object для создания человека с именем и фамилией (в этом случае полезно почитать про метод unapply вот это или на stackOverflow. Если не поленитесь разобраться и наряду с apply реализуете метод unapply для извлечения инициалов - вам будет отличная практика)
  //заметьте, никаких исключений программа выбрасывать не должна
  //
  //
  //Подсказки:
  //
  //считать входные данные можно через readLine()
  //регулярные выражения желательно знать, но мы все же немного облегчим вам задачу, указав, что вам потребуются + w < ? = \\ () А вот то, как это собрать вместе, это додумывать уже вам. Кстати, возможны и другие варианты.
  //Sample Input:
  //
  //John Doe
  //Sample Output:
  //
  //J. D.
//  val test_string: String = "John Doe"

  val NameSurname: String = scala.io.StdIn.readLine()

//  println(NameSurname.split(' ').mkString("Array(", ", ", ")"))

  def get_initials(name_surname: String): String = {

    val res = name_surname match {
      case name_surname: String if  name_surname.split(' ').length == 2 => return s"${name_surname.split(' ')(0).take(1).toUpperCase()}. ${name_surname.split(' ')(1).take(1).toUpperCase()}."
      case _=> return "Oops, something is wrong"
    }

    res
  }

  print(get_initials(NameSurname))


}