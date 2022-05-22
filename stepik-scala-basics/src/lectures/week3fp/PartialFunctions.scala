package lectures.week3fp

object PartialFunction extends App {
                                        //  Частичные функции (Partial functions)
  //С функций мы этот модуль начали, функциями и закончим.
  //
  //
  //
  //Часто бывает так, что требуется ограничить значения, которые можно подавать на вход функции. Конечно, можно выкрутиться через шаблоны. Так, например, мы допускаем только значения mon, fri, sun:
  //
  val whatToDo = (d: String) => d match {
    case "mon" => "Work!"
    case "fri" => "Party Time"
    case "sun" => "Relax a little"
  }
  //
  //
  //Однако есть и другое решение, которое основано на применении PartialFunction.
  //
  val aPartialFunction: PartialFunction[String, String] = {
    case "mon" => "Work!"
    case "fri" => "Party Time"
    case "sun" => "Relax a little"
  }
  //
  //
  //Если запустить функцию, то результат будет такой:
  //
  println(aPartialFunction("sun")) // Relax a little
//  println(aPartialFunction("sat")) // MatchError


  //Для недопустимого значения получаем MatchError - намек, что частичные функции основаны на совпадении шаблонов.
  //
  //Заметьте, что, как и в случае Function, последний из указываемых в квадратных скобках типов обозначает тип возвращаемого значения.

//  Проверка аргумента
  //Можно заранее узнать, допустимо ли использовать аргумент в функции или нет. В этом нам поможет isDefined:
  //

  println(aPartialFunction.isDefinedAt("tue")) // false
//  Частичные функции и orElse
  //Объединить несколько функций в цепь нам поможет orElse

  val pfChain: PartialFunction[String, String] = aPartialFunction.orElse[String, String] {
    case "sat" => "It's just Saturday"
  }
  println(pfChain("mon"))  // Work!
  println(pfChain("sat"))  // It's just Saturday

//Лифтинг
  //Можно сказать, что лифтинг позволяет поднять частичную функцию на следующий уровень. После lift - функция будет возвращать значения типа Option (т.е. решается проблема MatchError)
  //
  //
  //
    val aPartialFunction2: PartialFunction[String, String] = {
      case "mon" => "Work!"
      case "fri" => "Party Time"
      case "sun" => "Relax a little"
    }


    val lifted = aPartialFunction2.lift // теперь на выходе имеем тип Option[String]

    println(lifted("fri")) // Some(Party Time)
    println(lifted("thu")) // None

}

object Task1 extends App {
//  Напоследок - давайте создадим простенького чат-бота. Бот реагирует только на три фразы:
  //
  //hello
  //bye
  //what's up
  //Соответственно, его ответами будут :
  //
  //Hi, I'm Bot
  //Bye-bye
  //sup-sup
  //
  //
  //Примечание:
  //
  //ваша задача - использовать PartialFunction
  //и неплохо бы было предусмотреть, чтобы никаких MatchError не возникало (решение, где вручную указывается значение по умолчанию - не подойдет)
  //позаботьтесь, чтобы обратиться к функции можно было через переменную типа String => Option[String] с именем chatbot - иначе ваш код не пройдет тесты
  //можете сразу браться за написание требуемого кода, object Main уже присутствует в системе
  //никаких принтов вам прописывать не надо
  //подумайте, как решить задачу не через isDefinedAt()


  val chat_bot: PartialFunction[String, String] = {
    case "hello" => "Hi, I'm Bot"
    case "bye" => "Bye-bye"
    case "what's up" => "sup-sup"
  }
  val chatbot = chat_bot.lift

  val chatbot2 = ({
    case "hello"     => "Hi, I'm Bot"
    case "bye"       => "Bye-bye"
    case "what's up" => "sup-sup"
  }: PartialFunction[String, String]).lift  // можно и так записать


}