package lectures.week2oop

object Mistakes_and_Exceptions extends App {

//                                        Ошибки (Errors) vs Исключения (Exceptions)
  //Ошибки
  //
  //Указывают на то, что что-то не так с системой.
  //
  //StackOverflowError (с ней мы имели дело в теме рекурсий) ошибка связана со stack памятью, которая задействуется при вызове метода (под каждый вызов метода в стеке создается новый блок, хранящий информацию о параметрах, переменных метода)
  //
  //OutOfMemoryError также намекает, что мы исчерпали доступную нам память. Например, если в массиве вдруг стало слишком много элементов. Только в этот раз ошибка связана с heap памятью, которая используется при выделении памяти под объекты)
  //
  //
  //                                                Исключения
  //
  //Дают знать, что что-то не так с вашей программой.
  //
  //NullPointerException - возникает, если мы пытаемся получить доступ к чему-то, чего нет
    val x: String = null
    println(x.length) // NullPointerException
  //RuntimeException - бросается, когда необходимо указать на какие-то логические ошибки программы
  //
  //
  //Если в коде необходимо бросить исключение - достаточно использовать ключевое слово throw (за которым должно следовать new). Например:
  //
    throw new NullPointerException


//  Как поймать исключения
  //Тут нам поможет известный try-catch-finally

  def intOrNothing(hasException: Boolean): Int =
    if (hasException) throw new RuntimeException("Exception is here")
    else 200

  try {
    intOrNothing(true)
  } catch {
    case e: RuntimeException => println("RTE is here")
  } finally {
    println("I will be there no matter what")
  }

//  Единственное замечание: в идеале finally не должен содержать в себе ничего, кроме побочных эффектов
  //  ( информации для логов т.е. принты или запись инфы в файл, либо закрытие ресурсов после завершения работы с ними,
  //  например, закрытие файлов или соединения с базами данных)



//                                      Как создать свое собственное исключение
  //Тут ничего сложного - вспоминаем тему наследования классов. И создаем свой класс, который extends класс Exception. Далее создаем экземпляр класса, который при надобности можно бросать где угодно.
  //
    class MyException extends Exception

    val exception = new MyException
    throw exception

  //
  //Естественно, к своему классу с собственным исключением вы можете прикрутить все,
  // что только можно прикрутить к классу - методы, поля и т.д. Однако на практике вам это вряд ли особо понадобится,
  // так что слишком с этим не заморачивайтесь.




//                                             Тип Nothing
  //Мы не устанем повторять, что все в Scala является выражением. Поэтому если написать вот так:
  //
   val exceptionVal0 = throw new NullPointerException
  //а затем попытаться узнать тип переменной, то увидим Nothing



//  Nothing означает, что ничего нет - пустоту. А пустоту, если подумать, можно заполнить чем угодно.
//  Поэтому вполне можно в качестве типа переменной указать Int, а можно и String - да что угодно.
  //
   val exceptionVal1: Int = throw new NullPointerException
   val exceptionVal2: String = throw new NullPointerException
  //
  //
  //Если еще немного пофилософствовать - то все начинается с ничего(Nothing), которое затем становится чем-то.
  // Мы это к тому, что если увидите схемы с типами в Scala (вроде той, что мы приводим ниже) - вам теперь должно быть понятно,
  // почему в самом низу стоит Nothing и именно от него идут стрелки ко всему остальному.






//                                        Тип Any
  //Еще немного поговорим о типах. Если теперь сделаем вот так:
  //
    val potentialException = try {
      intOrNothing(false)
    } catch {
      case e: RuntimeException => println("RTE is here")
    } finally {
      println("I will be there no matter what")
    }
  //То potentialException в этот раз будет иметь тип Any:

//  Это объясняется тем, что необходимо угодить и тому, что будет возвращено в блоке try, и тому,
  //  что вернется в catch (finally не учитывается). Т.е. в нашем примере необходимо использовать универсальный тип,
  //  который позволил бы переменной хранить в себе как значения типа Int, так и Unit.
  //  И этим типом как раз будет AnyVal (можно запомнить как "готов к любому значению").
}



