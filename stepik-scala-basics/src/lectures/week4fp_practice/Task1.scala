object Task1 extends App {
//Осталось последнее задание. И ждет вас - разработка маршрутной сети для авиакомпании.
  //
  //Сеть будет довольно простой, но того функционала, что мы реализуем, должно хватить для тренировки. Опять же - желающие могут расширить функционал и получить готовый проект для своего резюме.
  //
  //Итак, наша сеть будет хранить ассоциации между пунктом отправления и всеми доступными для него пунктами назначения. Чтобы эти ассоциации создать, нужно сначала предоставить системе список возможных локаций.  Следовательно - главная переменная, с которой будет вестись работа - это наименование локации location .
  //
  //Необходимо реализовать не только основные методы для работы с сетью, но и дополнительные ( все же в реальной жизни без аналитики никуда, так почему бы не предусмотреть функционал, помогающий получить полезные для бизнеса данные).
  //
  //
  //
  //Основные методы:
  //
  //add(network, location) - добавляет локацию в маршрутную сеть
  //remove(network, location) - удаляет локацию из маршрутной сети
  //connect(network, pointA, pointB) - связывает две локации между собой (учитывать, что связь действует в обе стороны, т.е. становится доступным маршрут как туда, так и обратно)
  //disconnect(network, pointA, pointB) - удаляет маршрут
  //
  //
  //Дополнительные методы:
  //
  //nFlights(network, location) - возвращает количество доступных прямых перелетов из заданной точки
  //mostFlights(network) - возвращает точку, из которой доступно больше всего прямых перелетов
  //nLocationsWithNoFlights(network) - возвращает количество точек, не включенных ни в один маршрут
  //isConnected(network, pointA, pointB) - проверяет, связаны ли две точки между собой (учитывать возможные пересадки, необходимые чтобы перелететь из одной точки в другую)
  //
  //
  //Примечание: как вариант, можно сначала попробовать самостоятельно прописать весь требуемый функционал и только потом открывать следующие шаги этого модуля


//  1. location
//  2. add(network, location) - добавляет локацию в маршрутную сеть  :
////Map[String, Set[String]]  == network +(location ->Set())
//  3. remove(network, location) - удаляет локацию из маршрутной сети
////удаление указанной локации отовсюду, где только может встретиться - одним ключом(найденным по названию локации) не ограничиваемся
////удаление ключа из коллекции
////реализацию рекурсии
//  1. val disconnected = loop(network(location), network)
//  2. disconnected - location // удаляем ключ
//  3.// подчищаем маршруты
//  def loop(destinations: Set[String], acc: Map[String, Set[String]]): Map[String, Set[String]] =
//    if (destinations.isEmpty) acc
//    else loop(destinations.tail, disconnect(acc, location, destinations.head))
//  4.connect(network, pointA, pointB) - связывает две локации между собой (учитывать, что связь действует в обе стороны, т.е. становится доступным маршрут как туда, так и обратно)
//  5.disconnect(network, pointA, pointB) - удаляет маршрут

//  доп методы
//  6. nFlights(network, location) - возвращает количество доступных прямых перелетов из заданной точки
//  7. mostFlights(network) - возвращает точку, из которой доступно больше всего прямых перелетов
//  8. nLocationsWithNoFlights(network) - возвращает количество точек, не включенных ни в один маршрут
//  
//  network.view.filter(pair => pair._2.isEmpty).size
//
//  network.view.filterKeys(key => network(key).size == 0).size
//
//  network.view.filterKeys(key => network(key).isEmpty).size
//
//  network.count(pair => pair._2.isEmpty)
//
//  network.count(_._2.isEmpty)
//  9. isConnected(network, pointA, pointB) - проверяет, связаны ли две точки между собой (учитывать возможные пересадки, необходимые чтобы перелететь из одной точки в другую)




//Пора полностью собрать код. От вас требуется:
  //
  //написать реализацию всех основных методов
  //написать код всех дополнительных метотодов
  //вызывать методы и принтить результаты не надо
  //
  //
  //Примечание:
  //
  //disconnect по своей реализации похожа на connect
  //тело метода nFlights занимает всего одну строчку, полезно будет вспомнить про size
  //при написании mostFlights полезно будет воспользоваться maxBy
  //для isConnected рекомендуем реализовать алгоритм Breadth first search или поиск в ширину  с использованием таких методов, как .contain, .head, .tail
  //не надо прописывать object Main extends App и подобное - все сделано за вас, сразу приступайте к написанию кода задания
  //
  //
  //Основные методы:
  //
  //add(network, location) - добавляет локацию в маршрутную сеть
  //remove(network, location) - удаляет локацию из маршрутной сети
  //connect(network, pointA, pointB) - связывает две локации между собой (учитывать, что связь действует в обе стороны, т.е. становится доступным маршрут как туда, так и обратно)
  //disconnect(network, pointA, pointB) - удалят маршрут
  //
  //
  //Дополнительные методы:
  //
  //nFlights(network, location) - возвращает количество доступных прямых перелетов из заданной точки
  //mostFlights(network) - возвращает точку, из которой доступно больше всего прямых перелетов
  //nLocationsWithNoFlights(network) - возвращает количество точек, не включенных ни в один маршрут
  //isConnected(network, pointA, pointB) - проверяет, связаны ли две точки между собой (учитывать возможные пересадки, необходимые чтобы перелететь из одной заданной точки в другую)


  // Эт наша пустая сеть, которая нахуй никому не нужна(
  import scala.collection.mutable.Map
  var network: Map[String, Set[String]] = Map("Start"-> Set(""))

    def add(network:Map[String, Set[String]] , location:String):Map[String, Set[String]] = {
      network + (location -> Set())}

    def remove(network:Map[String, Set[String]], location: String): Map[String, Set[String]] = {
      // 1. удаление ключа из коллекции
      // 2. удаление указанной локации отовсюду, где только может встретиться - одним ключом(найденным по названию локации) не ограничиваемся
      // 3. реализацию рекурсии


      def loop(destinations: Set[String], acc: Map[String, Set[String]]): Map[String, Set[String]] =
        if (destinations.isEmpty) acc
        else loop(destinations.tail, disconnect(acc, location, destinations.head))
      val disconnected = loop(network(location), network)
      disconnected - location // удаляем ключ
      // // подчищаем маршруты
      //def loop(destinations: Set[String], acc: Map[String, Set[String]]): Map[String, Set[String]] =
      //    if (destinations.isEmpty) acc
      //    else loop(destinations.tail, disconnect(acc, location, destinations.head))
    }

    def connect(network:Map[String, Set[String]] , pointA:String, pointB:String): Map[String, Set[String]] = {
      // связывает две локации между собой (учитывать, что связь действует в обе стороны, т.е. становится доступным маршрут как туда, так и обратно)

      val routesForA: Set[String] = network(pointA) //получаем доступные маршруты для точки А
      val routesForB: Set[String] = network(pointB) //получаем доступные маршруты для точки B
      network + (pointA -> (routesForA + pointB)) + (pointB -> (routesForB + pointA)) //добавляем новый маршрут в сеть
    }

    def disconnect(network:Map[String, Set[String]] , pointA:String, pointB:String): Map[String, Set[String]] = {
      // Тут нужно пройтись по всем ключям - значениям сета location и удалить оттуда location  и удалить location, либо прописать пустой Set
      val routesForA: Set[String] = network(pointA)
      val routesForB: Set[String] = network(pointB)
      network + (pointA -> (routesForA - pointB)) + (pointB -> (routesForB - pointA))
    }

    def nLocationsWithNoFlights(network: Map[String, Set[String]]): Int = {network.count(pair => pair._2.isEmpty)}



    def nFlights(network: Map[String, Set[String]], location: String): Int = {
      //возвращает количество доступных прямых перелетов из заданной точки
      network(location).size}


    def mostFlights(network: Map[String, Set[String]]): String = {
      //возвращает точку, из которой доступно больше всего прямых перелетов
      network.toList.maxBy(_._2.size)._1
    }


    def isConnected(network: Map[String, Set[String]], pointA: String, pointB: String):Boolean ={
      //проверяет, связаны ли две точки между собой (учитывать возможные пересадки,
      // необходимые чтобы перелететь из одной заданной точки в другую)

      val routesForA: Set[String] = network(pointB).union(Set(pointB)) //получаем доступные маршруты для точки А
      val routesForB: Set[String] = network(pointA).union(Set(pointA)) //получаем доступные маршруты для точки B
      val union_routes: Set[String] = routesForA.union(routesForB)

      if (union_routes.size == routesForA.size + routesForB.size) return false
      else return true
    }



  ////////////// test
  println(network)

  println("Start to test funt add: Add point A, B, C")
  network=add(network , "A")
  network=add(network , "B")
  network=add(network , "C")
  println(network)
  println("Find locations with no flights")
  println(nLocationsWithNoFlights(network))
  println("Start to test connect A -> B & B->C")
  network=connect(network , "A", "B")
  network=connect(network , "C", "B")
  println(network)
  println("Start to Find location with most flights")
  val popular_loc: String = mostFlights(network)
  println(popular_loc)
  println("Find a number of most flights")
  println(nFlights(network,popular_loc))
  println("Test connection with A & C")
  println(isConnected(network, "A", "C"))
  println("Disconect B & C")
  network=disconnect(network, "B", "C")
  println(network)
  println("Test connection with A & C again")
  println(isConnected(network, "A", "C"))


}