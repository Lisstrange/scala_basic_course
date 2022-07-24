package lectures.week5_IS_basic


import scala.collection.immutable.ListMap

object TestPractice extends App {
  //  Дублирование элементов списка
  //Ваша задача - написать функцию duplicate(someList, nDups), которая создает указанное количество nDups дубликатов каждого элемента списка someList.
  //
  //Например, если был передан список List(1, 2, 3), а nDups = 2, то результатом будет List(1, 1, 2, 2, 3, 3)
  //
  //
  //
  //Примечание:
  //
  //тип списка, с которым предстоит работать, мы вам не сообщаем, так что полезно будет вспомнить про обобщения
  //одним из возможных вариантов решения этого задания является применение flatMap, а можно решить, полностью положившись на хвостовую рекурсию с .head и .tail - выбор за вами
  //напишите только код функции duplicate, вызывать ее и принтить результаты не надо
  //
  //
  //Sample Input:
  //
  //a b 2
  //Sample Output:
  //
  //List(a, a, b, b)

  val testList: List[Int] = List(1, 2, 3)
  val nDups: Int = 2

  def duplicate[A](someList: List[A], nDups: Int): List[A] = {
    someList.flatMap(List.fill(nDups)(_))
  }


  val testString: String = "Sst"
  var testMap: Map[Char, Int] = Map('t' -> 0)
  //Часть 1:
  //Напишите функцию countChars(someString): Map[Char, Int] , которая позволит посчитать, сколько раз каждый из символов был встречен в заданной строке. Результат функции требуется возвращать в виде Map(символ -> сколько раз встретился)
  //
  //Например, результатом выполнения countChars("None")  должно быть Map(n -> 2, o -> 1, e -> 1)
  testString.foreach(println(_))
    def countChars(someString: String): Map[Char, Int] = {
      val CharsArray = someString.toCharArray().map(_.toLower)

      def loop(CharArray: Array[Char], resMap: Map[Char, Int] = Map()): Map[Char, Int] = {
        CharArray match {
          case Array() => resMap
          case _ =>
            if (resMap.contains(CharArray(0))) loop(CharArray.drop(1), resMap + (CharArray(0) -> (resMap(CharArray(0)).intValue + 1)))
            else loop(CharArray.drop(1), resMap + (CharArray(0) -> 1))
        }
      }

      loop(CharsArray)

    }


  val CharsArray = testString.toCharArray()
  println(CharsArray.mkString("Array(", ", ", ")"))
  println(CharsArray(0))

  testMap = testMap + (CharsArray(0).toLower -> 1)
  println(testMap + (CharsArray(0).toLower -> (testMap('s').intValue + 1) ) )
  println(CharsArray(0).toLower)
  println(testMap.contains(CharsArray(0).toLower))
  println(testMap)

  println(CharsArray.drop(0).mkString("Array(", ", ", ")"))

  val aList: List[Char] = testString.toList
  println(testString.toList)

//  Проверка расстановки скобок в строке
  //Напишите функцию isBalanced(aString): Boolean, которая проверяет расстановку круглых скобок в строке: в строке не должно быть лишних скобок (т.е. количество открывающих скобок должно совпадать с закрывающими).
  //
  //Например: для строки d(e@5)s функция вернет true, а вот для строки (d)( выдаст false
  //
  //
  //
  //Примечание:
  //
  //кроме скобок в строке могут встречаться любые другие символы
  //не забываем оптимизировать код и использовать значение по умолчанию
  //напишите только код функции, принт результатов от вас не требуется
  //Sample Input:
  //
  //df(g)g)
  //Sample Output:
  //
  //false

//  def isBalanced(aString): Boolean = {}

  def isBalanced(aString:String): Boolean = {
    val aList: List[Char] = aString.toList // сделали список символов
    def loop(inputList: List[Char], cntLeftRoundBracket: Int = 0, cntRightRoundBracket: Int = 0 ): Boolean = {
      inputList match {
        case List() => {
          if (cntLeftRoundBracket==cntRightRoundBracket) true
          else false
        }
        case head::tail => {
          if (head == '(') loop(tail, cntLeftRoundBracket+1, cntRightRoundBracket)
          else if (head == ')') loop(tail, cntLeftRoundBracket, cntRightRoundBracket+1)
          else loop(tail, cntLeftRoundBracket, cntRightRoundBracket)
        }
      }
    }

  loop(aList)
  }


  println("%2.3f".format(100.2345)) // 1.235

//Реализуйте метод formatter, принимающий на вход два параметра:
  //
  //формат числа (должен иметь тип String), тогда, например, в качестве формата можно будет указать "%2.3f"
  //число, которое необходимо отформатировать (тип Double)
  //В качестве результата метод должен возвращать отформатированное число типа String.
  //
  //
  //
  //Далее воспользуйтесь написанной функцией formatter и задайте формат val simpleFormat, округляющий числа до двух знаков после запятой. При реализации этого шага вам стоит вспомнить про Eta-расширение. И учесть, что если в программе при тестировании задать список чисел numbers, то округление каждого числа из списка должно быть возможно через:
  //
  //  numbers.map(simpleFormat)
  //Соответственно, если в коде вместо simpleFormat задать preciseFormat, то должен сработать такой вызов:
  //
  //  numbers.map(preciseFormat)
  //
  //
  //Примечание:
  //
  //имплементация preciseFormat от вас не требуется, он дан в качестве примера, чтобы лишний раз напомнить о возможностях и преимуществах eta-расширения
  //напишите только код для simpleFormat и formatter, никаких принтов от вас не требуется
  //список numbers формируется в системе при тестировании, вам его создавать не надо
  //Sample Input:
  //
  //3.1415
  //Sample Output:
  //
  //List(3.14)
  //List(3.141500000000)

//  def formatter(format_num: String = "%2.3f", number: Double): String = {
//

//  }



  println("%2.7f".format(100.2345)) // 1.235

  def formatter(num_format: String) (num: Double): String = {


    num_format.format(num)
  }

  val simpleFormat: Double => String = formatter(num_format="%2.2f")

  print(simpleFormat(100.2345))
}

