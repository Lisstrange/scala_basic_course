package lectures.week3fp

class Options {
                                  //Опции (Options)
  //Опции - это всего лишь способ защиты от NullPointerException
  //
  //
  //Дела обстоят так, что не следует вручную делать проверки на Null - доверьте это дело Option, поместив в него переменную, которую подозреваете в отсутствии значения:
  //
    def unsafeMethod(): String = null
    def safeMethod(): String = "There is a String"
    val unsafeRes = Option(unsafeMethod())
    val safeRes = Option(safeMethod())

    println(unsafeRes) // None
    println(safeRes) // Some(There is a String)
  //Т.е. Option вернет None при отсутствии значения и Some со значением, если значение есть.
  //
  //
  //
  //Можно заранее оценить результат, вернув true или false:
  //
    val someOption: Option[String] = Some("Success")
    val noneOption: Option[Int] = None

    println(someOption.isEmpty) // false
    println(noneOption.isEmpty) // true


//  Option и orElse
  //Когда есть необходимость протестировать несколько методов одновременно с помощью Option, сделать код лаконичнее поможет конструкция orElse:


    def maybeSafeMethod(): String = "There is no harm"

    val chainedResult = Option(unsafeMethod()).orElse(Option(maybeSafeMethod()))
    println(chainedResult) // Some(There is no harm)


  //В качестве альтернативы - можно заложить проверку непосредственно в описываемый метод:


  val someVal = Some(2)
  println(someVal.map(_ * 2)) // Some(4)
  println(someVal.flatMap(x => Option(x * 2))) //Some(4)
  println(someVal.filter(x => x > 0)) //Some(2)
  println(someVal.filter(x => x > 10)) //None




}
