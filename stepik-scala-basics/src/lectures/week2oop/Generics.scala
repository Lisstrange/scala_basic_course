package lectures.week2oop

object Generics extends App {

  //                                                      Обобщения (Generics)
  //Допустим, вы написали программу, рассчитывающую сумму имеющихся у вас накоплений.
  // Программа прекрасно работает с числами типа Int. Но в один момент вы захотели увеличить точность расчетов и поюзать тип Double.
  // И что же тогда?
  //
  //Тогда можно, конечно, скопировать - вставить код, а затем везде в нем, где того требует логика, заменить Int на Double.
  // А вдруг станет нужен Float. И что - опять копировать?
  // Чувствуте, насколько идея не айс. И вот тут полезно будет вспомнить про обобщения.



  //                                                  Обобщения
  //Обобщения - это такая штука, которая позволяет нам использовать один и тот же код, но для разных типов данных.
  //
  //
  //
  //Поработаем немного со списками. Допустим, вы знаете, что придется хранить данные в виде списка.
  // Вот только тип этих данных вам пока неизвестен. А это - отличный повод воспользоваться обобщениями.
  // Тогда класс для работы со списками будем создавать вот так:
  //
  class MyList[A]
  //A в квадратных скобках означает тип данных, которые могут храниться в вашем списке.
  // Этот тип будет конкретизирован непосредственно в момент создания списка:
  //
  val listOfStrings = new MyList[String]
  val listOfDoubles = new MyList[Double]
  val listOfInts = new MyList[Int]
  //
  //
  //Примечание:
  //по соглашению в скобках [ ] обычно указываются заглавные буквы типа A, B, С, ...
  //обобщения применяются к классам(class), трейтам(trait),  но никак не к объектам (object)


  //                                  Обобщенные методы
  //Допустим, у нас есть метод, рандомно выбирающий число из целочисленного списка:
  def randomInt( items: List[Int]): Int = {
    val randomIndex = util.Random.nextInt(items.length)
    items(randomIndex)
  }
  println(randomInt(List(1,2,3,4,5)))

  //  Если мы хотим на вход подавать списки любого типа. Тогда имеет смысл создать обобщенный метод, переписав функцию вот так:
  def randomItem[A](items: List[A]): A = {
    val randomIndex = util.Random.nextInt(items.length)
    items(randomIndex)
  }

  println(randomItem(List("a", "bc", "def")))
  println(randomItem(List(1.5, 2.75, 3.8)))

  //                                            Вариативность (Variance problem)
  //Посмотрите на вот такую иерархию классов:

  class Fruit

  class Apple extends Fruit

  class Banana extends Fruit
  //Как думаете: если Apple расширяет класс Fruit, то можно ли подобное утверждать применительно к спискам List[Apple] и List[Fruit]?
  // Ответов на данный вопрос целых три. И связаны они с ковариантностью, инвариантностью и контравариантностью.
  //
  //Краткая шпаргалка по ковариантности, инвариантности и контравариантности представлена ниже,
  // а подробный разбор каждого случая будет дан в дальнейших шагах.

  //                              Инвариантность
  //Инвариантность диктует, что List[Apple] и List[Fruit] - это совершенно разные вещи, не связанные никакими родственными связями
  class InvariantList[A]
  val invariantFruitList: InvariantList[Fruit] = new InvariantList[Fruit]




  //                               Ковариантность
  //Ковариантность подразумевает, что раз Apple наследуется от Fruit, то и List[Apple] можно  рассматривать как потомка List[Fruit].
  //Ведь хотя у нас и список Fruit но на самом деле он создавался для одних яблок(new CovariantList[Apple]) - так не порушит ли банан
  // всю систему? На самом деле - нет. Просто из списка яблок - список превратится в то, чем и должен был быть изначально, в список фруктов.
  class CovariantList[+A]
  //Давайте напишем имплементацию метода add, чтобы точно видеть - какие типы, как и где указывать.

  //  class Fruit
  //  class Apple extends Fruit
  //  class Banana extends Fruit
  //
  //  val fruit: Fruit = new Apple
  //  val fruitList: CovariantList[Fruit] = new CovariantList[Apple]
  //  fruitList.add(new Banana)

  //Этим кодом мы говорим, что если в список типа A будет добавлен элемент типа B, то список типа A превратится в список типа B,
  // причем B является супер типом для A.
  val fruitList: CovariantList[Fruit] = new CovariantList[Apple]

  //                                Контравариантность

  class ContravariantList[-A]

  val contravariantList: ContravariantList[Apple] = new ContravariantList[Fruit]


}