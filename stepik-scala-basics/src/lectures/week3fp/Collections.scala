package lectures.week3fp

object Collections extends App {
  //Коллекции (Collections)
  //Пора познакомиться с основными коллекциями, доступными в Scala.
  //
  //В нашем распоряжении:
  //
  //Set ( т.е. без дубликатов или повторяющихся элементов)
  //Seq (т.е. у каждого элемента свой индекс, например - Vector, Range, List, Array)
  //Map (т.е. пары ключ-значение)



  //  map, flatMap, filter
  //  Смысл map заключается в том, что заданная функция применяется к каждому элементу списка.
  //
  //  flatMap очень похож на map, только он преобразует каждый элемент в целый список элементов и выполняет действия уже с ними, а потом результат собирает в одно целое.
  //
  //    Смотрите на преобразуетимер:

  val fruits = List("apple", "banana")

  val mapResult = fruits.map(_.toUpperCase)
  val flatResult = fruits.flatMap(_.toUpperCase)

  println(mapResult) // List(APPLE, BANANA)
  println(flatResult) // List(A, P, P, L, E, B, A, N, A, N, A)


  //  Именно из-за того, как работает flatMap, если нам требуется проставить точку после каждого символа строки
  //  и на выходе получить модифицированную строку, использовать придется именно его.

  val s = "Hello"
  val newStr: String = s.flatMap(c => (c + "."))
  println(newStr) // H.e.l.l.o.
  //  map тоже сработает, только вернет уже не строку

  println(s.map(c => (c + "."))) // ArraySeq(H., e., l., l., o.)




  //  Для начала разберем пример. Допустим, имеется следующий список:

  val progLanguages = List("java", "scala", "python")
  //  Для каждого элемента списка мы хотим вычислить длину строки. В результате у нас должен получиться список туплов: List((java,4), (scala,5), (python,6)), где каждый тупл состоит из двух элементов: название языка программирования и вычисленная длина строки.
  //
  //
  //
  //  Получить список туплов можно следующим образом:

  val out0 = for (lng <- progLanguages) yield (lng, lng.length)
  //  Возможно, кому-то более привычной покажется следующая запись:

  val out1 = for {
    lng <- progLanguages
  } yield (lng, lng.length)


  //  В качестве альтернативы for выражению можно использовать следующий код:

  val out2 = progLanguages.map(lng => (lng, lng.length))


  //  А теперь вместо списка туплов мы хотим получить просто список языков программирования,
  //  названия которых написаны большими буквами: List(JAVA, SCALA, PYTHON). Отметьте код, который позволит это сделать.



  //  Примечание:
  //
  //    подходящих вариантов ответа может быть несколько
  //      если бы мы записывали полученный результат в переменную, ее тип должен был быть List[String]


  val out_1 = for {
    lng <- progLanguages
  } yield lng.toUpperCase
//  println(out_1)

  val out_2 = for (lng <- progLanguages) println(lng.toUpperCase)
//  println(out_2)

  val out_3 = progLanguages.map(_.toUpperCase)
  println(out_3)
}


object Tast_List extends App {
  val progLanguages = List("java", "scala", "python")
  val lngAbbrev = List("JA", "SCA", "PY")

  val progLanguages2 = progLanguages.sorted
  val lngAbbrev2 = lngAbbrev.sorted

  println(progLanguages2)
  println(lngAbbrev2)

  val res1 = for {
    lng <- progLanguages2
  } yield ( lngAbbrev2(progLanguages2.indexOf(lng)), lng )

  println(res1)

  Array.ofDim[String](1).foreach(println) // null

  Array.ofDim[Boolean](1).foreach(println) // false

  Array.ofDim[Int](1).foreach(println) // 0

  val sampleTuple = new Tuple2(2, "Hello, World")
  println(sampleTuple.copy(_2 = "Scala").swap._1 + 5) // Scala5


  val list = List(1, 2, 3)
  val doubler = (x: Int) => List(x, x * 2)


  println(doubler) // <function1>

  println(list.flatMap(doubler))  // List(1, 2, 2, 4, 3, 6)
  println(doubler(5))  //  List(5, 10)
  println(list.map(doubler))  // List(List(1, 2), List(2, 4), List(3, 6))



  val nums1 = List(1, 2, 3)
  val nums2 = List(4, 6, 7)
  val nums3 = List(10, 100, 1000)


  val res_num = nums1
    .flatMap(a =>
      nums2
        .withFilter(b => b % 2 == 1)
        .flatMap(b =>
          nums3
            .map(c => (a + b) * c)
        )
    )


  for {
    a <- nums1
    b <- nums2 if b % 2 == 1
    c <- nums3
  } yield (a + b) * c

  nums1.flatMap(a => nums2.filter(_ % 2 == 1).flatMap(b => nums3.map(c => (a + b) * c)))

  println(res_num)
}