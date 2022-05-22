package lectures.week3fp

import scala.annotation.tailrec

class FunctionsFP {  //Функции, Функции высшего порядка

//  Что же такое функциональное программирование.
//  Это написание кода с использованием только чистых функций и неизменяемых переменных.

//  Чистая функция(pure function) - это когда
  //
  //1. результат работы функции зависит только от того, что она получает на вход, и описанного внутри нее алгоритма.
  // А значит, сколько бы раз такая функция ни была вызвана, например, для параметра х - результат ее работы будет неизменным

  //2. нет ни чтения, ни записи в файл или любого другого взаимодействия с внешним источником данных (файлом, базой данных, UI)


//  Примером чистой функции может служить уже известная вам toUpperCase. С другой стороны, System.nanoTime() - это уже не про чистые функции.

//  Так что, когда думаете о функциональном программировании, полезно вспомнить алгебру. Посмотрите на код, ничего не напоминает:
//  val a = f(x)
//  val b = y(a)
//  val c = z(b)
//  Такой стиль дает ряд преимуществ: так вы знаете, что а - это всегда f(x), поэтому b можно представить как y(f(x)).
  //  Аналогичные действия можно проделать и с c. Так что готовьтесь к тому, что ваш код будет напоминать следующий:
//  val c = z(y(f(x)))



                                                  //  Функции
  //Итак, пора начинать мыслить функционально и привыкать к тому, что теперь мы будем:
  //
  //использовать функции в качестве параметров
  //возвращать функции из функций
  //
  //
  //Допустим, нам потребовалась функция умножения числа на константу. Вспомнив ООП, где мы работаем с классами и их экземплярами, можно сообразить вот такой код:
  //
    class Multiplication {
      def multiply(x: Int): Int = x * 2
    }
  //
  //
  //Можно пойти еще дальше и, применив обобщенные классы, снять ограничения в использовании типов:
  //
    trait Multiplication1[A, B] {
      def multiplyh(x: A): B
    }
  //
  //
  //Но и это не будет пределом. Ведь есть еще apply:
  //
    trait Multiplication2[A, B] {
      def apply(x: A): B
    }
  //
  //
  //Теперь, если объявим переменную res:
  //
    val res = new Multiplication2[Int, Int] {
      override def apply(x: Int): Int = x * 2
    }
  //Обращаться к ней сможем так, что выглядеть это будет - как обращаение к функции:
  //
    println(res(2))
  //
  //
  //Довольно неплохо, правда? Особенно, если учесть, что подобные трейты уже прописаны в Scala за нас, чем можно вовсю пользоватьcя. В нашем распоряжении Function0, Function1, Function2, Function3 и так до Function22 включительно. Числа 0, 1, 2, 3, ..., 22 в конце слова Function - указывают на количество задаваемых параметров.
  //
  //
  //
  //Таким образом, для нашей задачи с одним передаваемым параметром прекрасно подойдет Function1[A, B]
  //
    val res1 = new Function1[Int, Int] {
      override def apply(x: Int): Int = x * 2
    }
  //
  //
  //Соответственно, если захотим передать два параметра, то тут уже пригодится Function2 (в качестве напоминания: третий Int в скобках у Function2 - обозначает тип возвращаемого значения):
  //
    val product = new Function2[Int, Int, Int] {
      override def apply(x: Int, y: Int): Int = x * y
    }

    println(product(3, 4) ) // выводит 12
}


object High_Order_Functions extends App {
//  Функции высшего порядка (High Order Functions)
//  Под функциями высшего порядка понимают такие функции, которые на вход получают другую функцию или же, как результат, возвращают функцию.
//
//
//
//  В качестве примера напишем функцию nTimes, которая получает на вход три параметра: f, x, n. f - это та функция, которая будет применена к параметру x указанное количество раз (в нашем случае это n раз).
//
//
//
//    Функцию nTimes можно написать несколькими способами. Способ 1, в котором мы передаем все три аргумента сразу - в одних скобках:

  @tailrec
  def nTimes(f: Int => Int, x: Int,  n: Int): Int = {
    if (n <= 0) x
    else nTimes(f, f(x), n - 1)
  }


//  В качестве функции, которую будем передавать в качестве аргумента, возьмем функцию, увеличивающую число на единицу:

  val increment = (x: Int) => x + 1
//  Тогда запуск функции nTimes будет выглядеть следующим образом:

  println(nTimes(increment, 0, 3)) // выведет 3



//                                      Каррированные функции (Currying Functions)
  //Перед тем, как мы рассмотрим второй способ написания функции nTimes, давайте разберемся, что же такое каррирование.
  //
  //Итак, под каррированной функцией подразумевают функцию, которая на вход принимает несколько аргументов (причем - можно сказать, что аргументы разбиты на группы). А в теле этой функции происходит серия вызовов функций, каждая из которых принимает единственный аргумент
  //
  //Опять же, обращаясь к алгебрe, расписать весь процесс можно примерно так:
  //
  //f1 = f(x)
  //f2 = f1(y)
  //result = f2(z)
  //Иначе говоря:
  //
  //result = f(x)(y)(z)

//Посмотрите на пример функции сложения. Можно написать так:
  //
    def add(x: Int, y: Int) = x + y

    println(add(1, 2)) // 3
  //А можно так (внимание на аргументы):
  //
    def add1(x: Int) = (y: Int) => x + y

    println(add1(1)(2)) // 3
  //Или еще вот так:
  //
    def add2(x: Int)(y: Int) = x + y

    println(add2(1)(2))




//  Есть функция, которая на вход принимает целое число, а возвращает функцию, которая возвращает сумму двух чисел.
  //
  //(Настоятельно советуем разобраться в этом коде и понять - что, где и почему)
    def someFunc: Int => Function1[Int, Int] = new Function1[Int, Function1[Int, Int]] {
      override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
         override def apply(y: Int): Int = x + y
      }
    }
  //Каким образом можно переписать вышеприведенную функцию?


  val res2 = someFunc(1)

  println(res2)
}





object final_test extends App {
  //  Зачастую наряду с именем программы используется еще и версия программы, что позволяет отслеживать историю внесения изменений.
  //
  //Версия программы может задаваться в разных форматах, но мы поработаем со случаем, когда версия задается последовательностью чисел, указывающих на масштаб вносимых в программу изменений.
  //
  //Например, для указания предварительных и неофициальных версий обычно указываются числа меньше 1, такие как 0.9. Первой версии программы присваивается номер 1.0, версия с небольшими изменениями может иметь номер 1.11, и только когда создается программа с новой функциональностью — версия увеличивается на 1 и становится 2.0.
  //
  //Ваша задача - написать метод, позволяющий сравнивать версии между собой: def compare(v1: String, v2: String): Int. Метод возвращает:
  //
  //-1 (если v1 < v2)
  //0 (если v1 = v2)
  //1 (если v1> v2)
  //Приведем несколько примеров:
  //
  //результатом сравнения 1.0.2.03 и 1.1.0 будет -1 (1.0.2.03 < 1.1.0, так как 0 < 1)
  //результатом сравнения 2.1 и 2.01 будет 0 (не забудьте предусмотреть в программе случаи, когда номера начинаются с нулей)
  //результатом сравнения 3.0 и 3.0.0.0 также будет 0
  //для 4 и 4.0.0.1 будет -1,  а для 4.0.1 и 4.0.0.1 будет 1
  //
  //
  //Примечание:
  //
  //напишите только код метода compare, его вызов и вывод результатов на экран вам делать не надо
  //Sample Input:
  //
  //0.9 1.0
  //Sample Output:
  //
  //-1
  //  1.0.2.03 и 1.1.0

  def compare1(v1: String, v2: String): Int = {
    val v1Normalized = normalize_ver(v1).replace(".", "0")
    val v2Normalized = normalize_ver(v2).replace(".", "0")

    println(v1Normalized, v2Normalized)
    (v1Normalized.length, v2Normalized.length) match {
      case (l1, l2) if l1 == l2 =>
        compareVersions(v1Normalized, v2Normalized)
      case (l1, l2) if l1 > l2 =>
        compareVersions(v1Normalized, (1 to l1 - l2).foldLeft(v2Normalized) { case (str, _) => str + "0"})
      case (l1, l2) if l2 > l1 =>
        compareVersions((1 to l2 - l1).foldLeft(v1Normalized) { case (str, _) => str + "0"}, v2Normalized)
    }
  }

  def compareVersions(in1: String, in2: String): Int = {
    (in1.toFloat, in2.toFloat) match {
      case (num1, num2) if num1 == num2 => 0
      case (num1, num2) if num1 < num2 => -1
      case (num1, num2) if num1 > num2 => 1
    }

  }
  def normalize_ver(version: String): String = {
    val version_arr: Array[String] = version.split('.')
    version_arr.map(_.replaceAll("^0+", "") ).mkString(".")
  }


  // начните писать ваш код здесь
  def compare2(v1: String, v2: String): Int ={

    v1.split("\\.") // разбивает по точкам v1
      .zipAll(v2.split("\\."), "0", "0") // то же для v2,
      // перепаковывает поэлементно соответсвующие позици в отдельные списки,
      // отсутвующие заменяет на '0' (для 1.0.1.1 и 1.0 будет (1,1)(0,0)(1,0)(1,0))
      .find {case(a, b) => a != b } //ищет первое различие
      .fold(0) { case (a, b) => a.toInt - b.toInt } // попробовал вычитать b из a
  }

  def compare3(v1: String, v2: String): Int = {
    v1.split("\\.")
      .zipAll(v2.split("\\."), "0", "0")
      .find { case (a, b) => a != b }
      .map { case (a, b) => a.toInt.compareTo(b.toInt) }
      .getOrElse(0)
  }


  val test_1: String = "03.00.232.3.04"
  val test_2: String = "3.00.2.3400.043"


  println(compare1(test_1, test_2))
  println(compare3(test_1, test_2))
  println(compare2(test_1, test_2))

}