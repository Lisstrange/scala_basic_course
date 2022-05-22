Объект
Создание объекта очень напоминает создание класса только с ключевым словом object.
```
  object Number
```

Закономерный вопрос - а зачем это надо?

Так вот, может статься, что потребуется создать переменные или методы, доступные в любое время, причем без дополнительного объявления экземпляров класса (что-то такое универсальное).

 

Например, следующий код позволит нам без особых усилий использовать заданное число Pi в любом месте нашего кода.
```
  object Number {
    val Pi = 3.14
  }

  println(Number.Pi)
```


### Объекты vs Классы
Отличительно чертой объектов является то, что они:

не имеют параметров
являются одиночками (Singleton Object)
Когда мы говорим "одиночки" мы подразумеваем, что объекты существуют в единственном экземпляре. Можно, кстати, еще добавить, что у них свой тип (например, наш объект Number, взятый в качестве примера, типа Number)

![image](https://user-images.githubusercontent.com/47192124/169690800-b78e3cae-36e6-4b6e-b821-c6533b7b5e57.png)

Если кому интересно, утверждение про одиночек может быть легко проверено:
```
  val numA = Number
  val numB = Number

  println(numA == numB) // выведет true
```
True, выведенное в результате, свидетельствует о том, что numA и  numB ссылаются на один и тот же  Number.

 

А вот если подобный эксперимент провести для класса, то результат будет отрицательным, т.к. теперь дело будем иметь с двумя разными экземплярами класса.
```
  class Number   
 
  val numA = new Number
  val numB = new Number

  println(numA == numB) // выведет false
```
### Компаньоны
Если в одном и том же файле и под одним и тем же именем объявить объект и класс, то в таком случае их можно смело назвать компаньонами.

Объект имеет доступ ко всем полям и методам своего класса-компаньона (в том числе и private).

![image](https://user-images.githubusercontent.com/47192124/169690829-e8b93d91-4faf-4f06-b8f7-306f0633cbda.png)

```
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
```

Дополнительная иллюстрация (код тот же, но цель - подробнее показать работу вызываемых методов):



 ![image](https://user-images.githubusercontent.com/47192124/169690842-1d6be928-e30f-4aed-8966-663c94c2905e.png)


В качестве бонуса: если оптимизировать код(все-таки писался он больше в учебных целях) и приблизить его к тому, как он должен выглядеть в идеале, получится так:
```
  class MyString(val str: String) {
    private var extra = "extraData"
    override def toString: String = str + extra
  }

  object MyString {
    def apply(base: String, extras: String): MyString = {
      val s = new MyString(base)
      s.extra = extras
      s
    }

    def apply(base: String) = new MyString(base)
  }
 
  // опускаем наименование метода apply и сразу пишем необхоимые для этого метода параметры
  println(MyString("hello", "world")) // helloworld
  println(MyString("welcome")) // welcomeextraData
```
Смотрите, тут два ключевых момента, оба связаны с синтаксическим сахаром (подробно о нем поговорим чуть позже в этом модуле):

apply можно не прописывать, а сразу после имени объекта в скобках указывать параметры apply метода (позже apply будет разбираться более подробно)
переименовав getString в toString, мы избавили себя от необходимости вообще прописывать имя метода для его вызова


### Фабричный метод (Factory method)
У объекта, как мы выяснили ранее, отсутствуют передаваемые параметры.

Но если все же надо как-то имплементировать усложненный интерфейс для создания объекта, а конструктор класса менять не айс, то выкрутиться поможет фабричный метод.

Смотрите:
![image](https://user-images.githubusercontent.com/47192124/169690872-5507f0f0-2452-4bae-98c2-97d4d5c070d4.png)

Кстати, можно сделать наш код еще лаконичнее, если переименовать newNum в apply, что позволит опускать имя метода apply при вызове (это на практике и делается):
```
  class Number(val num: Int)

  object Number {
    val Pi = 3.14

    def apply(x: Number, y: Number): Number = new Number(x.num + y.num)
  }

  val numA = new Number(1)
  val numB = new Number(2)

  val numC = Number(numA, numB) // применяем apply

  println(numA.num) // 1
  println(numB.num) // 2
  println(numC.num) // 3
```
