package lectures.week1basics

object Functions extends App {

  def aParameterlessFunction(): Unit = println("Function with no parameters")

  aParameterlessFunction()
  aParameterlessFunction



  //  Параметры по умолчанию
  //  В Scala мы можем задавать дефолтные значения для параметров, что позволит нам лишний раз не указывать параметры при вызове
  //  функции. Только следите за порядком аргументов.

  def aFunctionWithDefaultParameter(x: Int, y: String = "Default Parameter"): String = {
    s"x = $x and y = $y"
  }

  println(aFunctionWithDefaultParameter(1)) // выводит x = 1 and y = Default Parameter


//  Вызов по имени(call-by-name) vs Вызов по значению(call-by-value)
//  Чтобы увидеть разницу между этими двумя вызовами (кроме того, что рядом с параметром, вызываемым по имени, печатается знак => )давайте поработаем с кодом:


  def callByValue(x: Long): Unit = {
    println(s"call by value: x1 = $x")
    println(s"call by value: x2 = $x")
  }

  def callByName(x: => Long): Unit = {
    println(s"call by name: x1 = $x")
    println(s"call by name: x2 = $x")
  }

  callByValue(System.nanoTime())
  callByName(System.nanoTime())


//Если для callByValue на экран выводятся два одинаковых значения, то для callByName значения будут разными.
//Это объясняется разницей в подходах к вычислению значений параметров.
//Вызов по значению подразумевает вычисление значения переданного выражения перед вызовом функции.
//Преимущество: значение вычисляется только один раз.
//  Вызов по имени подразумевает вычисление значения выражения в момент его вызова в функции
//  Преимущество: значение не вычисляется, если не используется в теле функции.
//Вложенные функции
//Еще одним моментом, который бы хотелось отметить - является возможность определить функцию или даже несколько функций внутри одной функции и там же ее(или их) вызвать.

  def aBossFunction(): String = {
    def aHelperFunction(): String = "I'm here to help"

    aHelperFunction()
  }



  // исправьте код
  def aCondition(): Boolean = if ( 1 < 2) true else false

  def someFunnc(x: Int = 3, y: => Int): Int = {
    if (aCondition) x * 2
    else  y
  }



  println(someFunnc(3, 4))
}