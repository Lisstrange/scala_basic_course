package exercises

object Logger extends App {
  //  Начнем с получения текущего количества сообщений. Для этого мы написали метод count
  class Logger(msgNum: Int) {

//    this = new Logger(msgNum = 1)


    def count = msgNum
    //  Изначально у нас ноль сообщений, так что упрощаем инициализацию и добавляеем значение по умолчанию.
    //  Раз можно сразу получить доступ к полю класса, предварительно поставив val перед параметром, то незачем прописывать лишние методы.

    //Переходим к обработке сообщений. В методе info пропишем вывод сообщения на экран и, соответственно, увеличение текущего количества  сообщений на единицу
    //
    //Отметьте нужные строки кода, что нужно поместить в метод
      def info: Unit = {
        new Logger(msgNum + 1)
        println("INFO New Message")
    }

    //Последнее, что осталось предусмотреть - это обработку сразу нескольких сообщений.
    //
    //Как думаете, как это лучше сделать?

    //    Делаем перегрузку. В целом-то смысл метода тот же, что и у info, только теперь на вход задаем количество поступивших сообщений.
    //    Так что незачем создавать метод с новым именем
      def info(n: Int ): Any = {
        if (n <= 0) this
        else info(n - 1)


    // Мы уже знаем, что для того, чтобы что-то сделать n-раз, следует воспользоваться рекурсией.
      // Так что обработку поступления сразу нескольких сообщений (в перегруженном методе info) мы будем реализовывать именно через рекурсию.
    //
    //Какой код подойдет?
      //if (n <= 0) this
      //else info.info(n - 1)

      //Представьте, что мы полностью собрали наш код, дополнительно прописав в классе метод print
      //
        def print = println(msgNum)
      //
      //Какой код позволит получить следующий результат?

//      INFO New Message
//      1
//      INFO New Message
//      INFO New Message
//      INFO New Message
//      3
//      INFO New Message
//      INFO New Message
//      INFO New Message
//      3


//      val logger = new Logger
//      logger.info.print
//      logger.info(3).print
//      logger.info.info.info.print

//      ИЛИ

//      val logger = new Logger
//      logger.info.print
//      logger.info.info.info.print
//      logger.info.info.info.print
    }
}

}





object FinalTask extends App {
  //Мы прошлись по всем методам. Пора собрать наш код.
  //
  //Напишите код класса Logger, который
  //
  //предоставит пользователю возможность получить текущее количество сообщений
  //содержит в себе метод info, выводящий на экран сообщение INFO New Message и увеличивающий число сообщений в системе на 1
  //содержит перегруженный метод info, выводящий указанное выше сообщение INFO New Message заданное число раз
  //содержит метод print для получения текущего количества сообщений (def print: Unit)
  //
  //
  //Примечание:
  //
  //ваша задача - написать только код класса, создавать экземпляр класса не надо
  //учтите все оптимизации, рассмотренные на предыдущих шагах

  class Logger(val msgNum: Int = 0) {
    def info: Logger = {
      println("INFO New Message")
      new Logger(msgNum + 1)

    }

    def info(n: Int): Logger = {

      def loop(n: Int): Logger = {
        if (n <= 0) this // == this
        else  info.info(n - 1)
      }
      loop(n)

    }

//    содержит метод print для получения текущего количества сообщений (def print: Unit)
    def print: Unit = println(msgNum)

  }

  val logger = new Logger
  logger.info.print
  //      INFO New Message
  //      1


  logger.info(3).print
//  logger.info.info.info.print
}