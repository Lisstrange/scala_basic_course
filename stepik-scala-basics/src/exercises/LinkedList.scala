package exercises

object LinkedList extends App {
//  подобную реализацию вы вряд ли будете с нуля собирать на практике.
  //  Именно этот пример взят больше для того, чтобы подготовить вас к возможным вопросам на собеседовании, связанных со списками


//  Получается, теперь наши сообщения будут храниться в односвязном списке, так что стоит предусмотреть следующие методы:
  //
  //last - возвращает последнее поступившее сообщение (иначе говоря - head списка)
  //previous - возвращает все ранее поступившие собщения(это будет tail или хвост списка), за исключением самого последнего (т.е. за исключением head)
  //isEmpty - проверяет, если ли в нашем списке сообщения
  //all - предоставляет нам содержимое нашего списка в виде строки
  //add(msg) - добавляет сообщение в список
  //
  //
  //Примечания:
  //
  //для поступающих сообщений используем тип String
  //описанные выше методы разместим для начала в классе LogList
  //
  //
  //Исходя из полученного описания,  сопоставьте методы и соответствующие им типы возвращаемых значений.

  abstract class  LogList() {
//    В коде присутствует
//
//LogList(тут указаны требуемые нам методы)
//Log (хранит методы для непустого списка)
//Empty (описывает методы для пустого списка)
//Сможете сопоставить, что является чем?

//    Log и Empty содержат в себе одинаковые методы, которые просто применяются по-разному в зависимости от ситуации.
//    Логично, что Log и Empty имеют один и тот же супер класс.
//    LogList является абстрактным, т.к. нам нужен класс, который бы содержал в себе список методов,
//    которые необходимо реализовать по-разному в соответсвующих подклассах
    def all(): String

    def last: String

    def previous: LogList

    def add: LogList

    def isEmpty: Boolean
  }


//abstract class LogList - ничего не требуется, можно открывать скобки {}
  //object Empty - extends LogList
  //class Log - (head: String, tail: LogList) extends LogList


//  Метод add имеет разную имплементацию - в зависимости от того, прописан он в Log или Empty.
  //
  //Укажите, где - чья реализация метода:



  //def add(msg: String): LogList = new Log(msg, Empty) - Empty
  //def add(msg: String): LogList = new Log(msg, this)  - Log
  //def add(msg: String): LogList                       - LogList


  //Давайте убедимся, что вы точно поняли, как работает односвязный список. Какой код сконструирует список a, b, c ?
  //
  //Примечание: a - является "головой" списка

//  val list = new Log("a", new Log("b", new Log("c", Empty)))
//
//  val list = new Log("b", new Log("c", Empty))
//  val newList = list.add("a")

}

object TaskLinkedList extends  App {
  //Пора собрать код. Продублируем еще раз условие:
  //
  //last - возвращает последнее поступившее сообщение (иначе говоря - head списка)
  //previous - возвращает все ранее поступившие собщения(это будет tail или хвост списка),
  // за исключением самого последнего (т.е. за исключением head)
  //isEmpty - проверяет, если ли в нашем списке сообщения
  //all - предоставляет нам содержимое нашего списка в виде строки
  //add(msg) - добавляет сообщение в список
  //
  //
  //Примечание:
  //
  //создавать экземпляр класса не требуется, напишите только код для LogList, Log, Empty
  //Empty не содержит в себе элементов, поэтому для previous и last бросает исключение NoSuchElementException
  //all, описанный в Empty возвращает пустую строку, а all, описанный в Log, требует написания рекурсивной функции,
  // результатом которой будет строка, содержащая все поступившие в систему сообщения (сообщения разделены пробелом,
  // но начинаться с пробела итоговая строка не должна)
  //например, даны сообщения new Log(“m1”, new Log(“m2”, new Log(“m3”, Empty))),
  // тогда результатом работы метода all должна быть строка  m1 m2 m3

  abstract class LogList {  //тут указаны требуемые нам методы
    def all: String  //all - предоставляет нам содержимое нашего списка в виде строки
    def last: String  //last - возвращает последнее поступившее сообщение (иначе говоря - head списка)
    def previous: LogList //previous - возвращает все ранее поступившие собщения(это будет tail или хвост списка), за исключением самого последнего (т.е. за исключением head)
    def add(msg: String): LogList //add(msg) - добавляет сообщение в список
    def isEmpty: Boolean //isEmpty - проверяет, если ли в нашем списке сообщения
  }



  class Log(val head: String, val tail : LogList) extends LogList { //хранит методы для непустого списка


    def all: String = {
      def loop(log: Log) : String = {
        if (log.previous == Empty) {
          println(s"Конечное значение = ${log.last}")
        log.last
        }
        else {
          println("Сейчас head имеет значение:" + log.last + " ")
          loop(new Log(head = log.last + " " + log.previous.last , tail = log.previous.previous) )
        }
      }
      val res: String = loop(log = Log.this)
      res
    }  // Тут будет рекурсия


    def last: String = head
    def previous: LogList = tail  // Вычесть последнее значенией из списка
    def add(msg: String): LogList = {new Log(head = msg, tail = this) }
    def isEmpty: Boolean = false
  }
  //Метод add имеет разную имплементацию - в зависимости от того, прописан он в Log или Empty.
  //  Empty (описывает методы для пустого списка)
  //  Log и Empty содержат в себе одинаковые методы, которые просто применяются по-разному в зависимости от ситуации.
  //  Логично, что Log и Empty имеют один и тот же супер класс.
  //  LogList является абстрактным, т.к. нам нужен класс, который бы содержал в себе список методов,
  //  которые необходимо реализовать по-разному в соответсвующих подклассах

  object Empty extends LogList { //хранит методы для пустого списка
    def all: String = ""
    def last: String = throw new NoSuchElementException
    def previous: LogList = throw new NoSuchElementException //{new Log(head = "", tail = Empty)}  // Вычесть последнее значенией из списка
    def add(msg: String): LogList = {new Log(msg, Empty)}
    def isEmpty: Boolean = true
  }


  val list = new Log("INFO_1", new Log("INFO_2", new Log("INFO_3", Empty)))


  println(list.previous.last)
  println(list.all)
//  Мы создали список
  //
  //  val list = new Log("INFO_1", new Log("INFO_2", new Log("INFO_3", Empty)))
  //
  //Какой элемент выведется на  экран в результате исполнения кода:
  //
  //  println(list.previous.last)
  //
  //Примечание: ответ следует вводить без кавычек
  //Ответ
//  INFO_2
}
