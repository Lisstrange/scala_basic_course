package lectures.week3fp

object ExceptionsFP extends App {
//                                                  Исключения
  //Из ООП мы знаем о существовании try-catch-finally, что неплохо работает в маленьких программках,
  // но в реальности код, с которым придется иметь дело, будет гораздо сложнее.
  // И если переусердствовать с try-catch-finally, то получится код, читать который со временем будет все сложнее  и сложнее.
  //
  //Поэтому на помощь в функциональном программировании нам приходит Try
//Try
  // Чтобы Try стал доступен, его необходимо предварительно импортировать:
  //
    import scala.util.Try
//  Далее особых сложностей в его использовании нет.
  //  Просто пишем Try, а затем в круглых скобках указываем метод, который было бы желательно проверить:

  def unsafeMethod(): String = throw new RuntimeException("Sorry, not your day")

  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure) // Failure(java.lang.RuntimeException: Sorry, not your day)

//  В качестве альтернативы - можно писать Try, затем через пробел открывать фигурные скобки, в которых прописывать код для тестирования.
  // val anotherPotentialFailure = Try {
  //   // код, содержащий исключения
  // }

//  Еще одна удобная штука - это то, что мы можем заранее проверить, содержит ли метод исключения, получив в ответ true или false:

  println(potentialFailure.isSuccess) // false

//  Try и orElse
  //Когда есть необходимость протестировать несколько методов одновременно с помощью Try, сделать ваш код лаконичнее поможет конструкция orElse. Смотрите:

    def myMethod(): String = "My method is valid"

    val executeWithTry = Try(unsafeMethod()).orElse(Try(myMethod()))

    println(executeWithTry) // Success(My method is valid)

}
