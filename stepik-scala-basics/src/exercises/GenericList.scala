package exercises
//Давайте возьмем программу из Задания 2 и на ней потренируемся в применении обобщений.
//
//Если вы все-таки чувствуете, что тема обобщений вами не до конца усвоена,
// предлагаем вам ознакомиться с дополнительной теорией по обобщениям из курса по продвинутой Scala
//
//Допустим, теперь мы не знаем, будем ли хранить в списке логов текстовую информацию или это будут какие-то коды, причем целочисленного типа.
//
//Задача, стоящая перед нами теперь: модифицировать код так, чтобы все методы отлично работали.
// Независимо от того, список какого типа мы предпочтем для хранения информации.
object GenericList extends App {


  abstract class LogList[+A] {  //Указан нужный метод
    def all: String  //all - предоставляет нам содержимое нашего списка в виде строки
    def last: A  //last - возвращает последнее поступившее сообщение (иначе говоря - head списка)
    def previous: LogList[A] //previous - возвращает все ранее поступившие собщения(это будет tail или хвост списка), за исключением самого последнего (т.е. за исключением head)
    def add(msg: String): LogList[Any] //add(msg) - добавляет сообщение в список
    def isEmpty: Boolean //isEmpty - проверяет, если ли в нашем списке сообщения
  }



  class Log[+A](val head: A, val tail : LogList[A]) extends LogList[A] { //хранит методы для непустого списка


    def all: String = {
      def loop(log: LogList[A]) : String = {
        if (log.isEmpty) ""
        else log.last + " " + loop(log.previous)
      }
      loop(this)
    }  // Тут будет рекурсия


    def last: A = head
    def previous: LogList[A] = tail  // Вычесть последнее значенией из списка
    def add(msg: String): LogList[Any] = {new Log(head = msg, tail = this) }
    def isEmpty: Boolean = false
  }
  //Метод add имеет разную имплементацию - в зависимости от того, прописан он в Log или Empty.
  //  Empty (описывает методы для пустого списка)
  //  Log и Empty содержат в себе одинаковые методы, которые просто применяются по-разному в зависимости от ситуации.
  //  Логично, что Log и Empty имеют один и тот же супер класс.
  //  LogList является абстрактным, т.к. нам нужен класс, который бы содержал в себе список методов,
  //  которые необходимо реализовать по-разному в соответсвующих подклассах

  object Empty extends LogList[Nothing] { //хранит методы для пустого списка
    def all: String = ""
    def last: Nothing = throw new NoSuchElementException
    def previous: LogList[Nothing] = throw new NoSuchElementException //{new Log(head = "", tail = Empty)}  // Вычесть последнее значенией из списка
    def add(msg: String): LogList[Any] = {new Log(msg, Empty)}
    def isEmpty: Boolean = true
  }

  val intLogs: LogList[Int] = Empty
  val strLogs: LogList[String] = Empty

}
