package lectures.week2oop

object ObjectsOOP extends App {
//  Объект
//Создание объекта очень напоминает создание класса только с ключевым словом object.
//Закономерный вопрос - а зачем это надо?
  //
  //Так вот, может статься, что потребуется создать переменные или методы, доступные в любое время, причем без дополнительного объявления экземпляров класса (что-то такое универсальное).
  //
  //
  //
  //Например, следующий код позволит нам без особых усилий использовать заданное число Pi в любом месте нашего кода.
  //
    object Number1 {
      val Pi = 3.14
    }

    println(Number1.Pi)


//  Объекты vs Классы
//  Отличительно чертой объектов является то, что они:
//    не имеют параметров
//  являются одиночками (Singleton Object)


//  Когда мы говорим "одиночки" мы подразумеваем, что объекты существуют в единственном экземпляре.
  //  Можно, кстати, еще добавить, что у них свой тип (например, наш объект Number, взятый в качестве примера, типа Number)

//  Если кому интересно, утверждение про одиночек может быть легко проверено:

  val numA1 = Number1
  val numB1 = Number1

  println(numA1 == numB1) // выведет true
//  True, выведенное в результате, свидетельствует о том, что numA и  numB ссылаются на один и тот же  Number

//  А вот если подобный эксперимент провести для класса, то результат будет отрицательным, т.к. теперь дело будем иметь с двумя разными экземплярами класса.

  class Number2

  val numA2 = new Number2
  val numB2 = new Number2

  println(numA2 == numB2) // выведет false


//                                          Компаньоны
//  Если в одном и том же файле и под одним и тем же именем объявить объект и класс, то в таком случае их можно смело назвать компаньонами.
//
//    Объект имеет доступ ко всем полям и методам своего класса-компаньона (в том числе и private).

  class MyString(val str: String) {
    private var extra = "extraData"

    def getString: String = str + extra
  }

  object MyString {
    def apply(base: String, extras: String): MyString = {
      val s = new MyString(base)
      s.extra = extras
      s
    }

    def apply(base: String) = new MyString(base)
  }

  println(MyString.apply("hello", "world").getString)
  println(MyString.apply("welcome").getString)

//В качестве бонуса: если оптимизировать код(все-таки писался он больше в учебных целях)
  // и приблизить его к тому, как он должен выглядеть в идеале, получится так:


  class MyString_(val str: String) {
    private var extra = "extraData"
    override def toString: String = str + extra
  }

  object MyString_ {
    def apply(base: String, extras: String): MyString_ = {
      val s = new MyString_(base)
      s.extra = extras
      s
    }

    def apply(base: String) = new MyString_(base)
  }

  // опускаем наименование метода apply и сразу пишем необхоимые для этого метода параметры
  println(MyString_("hello", "world")) // helloworld
  println(MyString_("welcome")) // welcomeextraData


//  Смотрите, тут два ключевых момента, оба связаны с синтаксическим сахаром (подробно о нем поговорим чуть позже в этом модуле):
  //
  //apply можно не прописывать, а сразу после имени объекта в скобках указывать параметры apply метода (позже apply будет разбираться более подробно)
  //переименовав getString в toString, мы избавили себя от необходимости вообще прописывать имя метода для его вызова
  //



//                                          Фабричный метод (Factory method)
  //У объекта, как мы выяснили ранее, отсутствуют передаваемые параметры.
  //Но если все же надо как-то имплементировать усложненный интерфейс для создания объекта, а конструктор класса менять не айс, то выкрутиться поможет фабричный метод.
  //Смотрите:

  class Number3(val num: Int)

  object Number3 {
    val Pi = 3.14

    def apply(x: Number3, y: Number3): Number3 = new Number3(x.num + y.num)
  }

  val numA3 = new Number3(1)
  val numB3 = new Number3(2)

  val numC3 = Number3(numA3, numB3) // применяем apply

  println(numA3.num) // 1
  println(numB3.num) // 2
  println(numC3.num) // 3



}
