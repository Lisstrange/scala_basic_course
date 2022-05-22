package lectures.week1basics

import scala.annotation.tailrec

object Recursion extends App {
  //  Циклы
  var i = 0
  while (i < 3) {
    println("hello")
    i += 1
  }

  println(i)
  //  Вот только делать так не надо. Просто знайте - так делать можно, но не надо
  //  Особенно если хотите мастерски владеть функциональным программированием.
  //
  //  Мы и for вам пока рекомендуем оставить в стороне. Почему? А все потому, что возникает необходимость использовать var переменные
  //  - а мы в функциональном программировании в этом не заинтересованы, т.к. работаем с неизменяемыми переменными - т.е. с val.

  //  Вместо циклов используем рекурсии!!
  def factorial(n: Int): Int = {
    if (n <= 0) 1
    else n * factorial(n - 1)
  }

  println(factorial(3)) // выводит 6

  //  Как это работает: промежуточные вычисления хранятся в стеке, а это значит, что каждый вызов рекурсивной функции сопряжен с использованием стека.
  //  Давайте немного перепишем код, чтобы видеть, о чем идет речь:

  def factorial_(n: Int): Int = {
    if (n <= 0) 1
    else {
      println(s"Имеем число $n, для которого считаем факториал ${n - 1}")
      var res = n * factorial(n - 1)
      println(s"Вычислили факториал $n")

      res
    }
  }

  factorial_(3)


  //                        Хвостовая рекурсия (Tail Recursion)
  //  Отличительной особенностью функции с хвостовой рекурсией является то, что последней операцией идет вызов этой самой функции или константа.
  //  И теперь промежуточные вычисления накапливаются в аккумуляторе.

  //  Особенность в том, что хвостоая рекурсия не заполняе стек вызовов - а реализуется с помощью цикла. Поэтому она не падает по StackOverflowError

  def factorialWithTailRecursion(n: Int): Int = {
    @tailrec
    def loop(x: Int, accumulator: Int): Int = {
      if (x <= 1) accumulator
      else loop(x - 1, x * accumulator)
    }

    loop(n, 1)
  }

  //  Как писать функции в стиле хвостовой рекурсии
  //  1. Определяете функцию. Назовем ее основной.
  //
  //  2. Внутри основной функции прописываете еще одну функцию. Назовем ее вспомогательной.
  //
  //  3. В качестве аргументов вспомогательной функции указываете число вызовов (этот аргумент совпадает с одним из аргументов основной функции) - плюс аккумулятор, который будет содержать конечный результат.
  //
  //  4.  Прописываете код с рекурсией в теле вспомогательной функции с использованием аккумулятора (так, чтобы значение постепенно накапливалось)
  //
  //  5. Вызываете вспомогательную функцию из основной функции. Не забудьте указать начальное значение аккумулятора, задающее отправную точку всех вычислений. Используйте аргумент по умолчанию для оптимизации кода.

//  Вывод слова n раз
//    Помните, в самом начале мы написали while-цикл для вывода слова указанное число раз.
  //    Пришло время переписать этот пример, использовав хвостовую рекурсию.

  def repeatWord(word: String, n: Int): String = {
    @tailrec
    def loop(i: Int, acc: String = word): String = {
      if (i <= 1) acc
      else loop(i - 1, s"$word $acc")
    }

    loop(n)
  }






  def powerOfTwo(degree: Int, number: Int = 2): BigInt = {
    @tailrec
    def loop(x: Int, acc: BigInt = 2): BigInt = {
      if (x <= 1) acc
      else loop(x-1, acc*2)

    }

    loop(degree)
  }






  val x: Int = 10
  val y: Int = 1
  val n: Int = 5

  val n_repeat : Int = (x + y).toString.length

  @tailrec
  def loop(x: Int,y: Int, n : Int) : Int = {
    if (n == 0) x
    else loop(x= (x + y), y=y, n=(n-1))


  }

  val res: String = loop(x,y,n).toString + " "

  println(x,y,n,n_repeat, res)
  print(res * n_repeat + "is the result")
}