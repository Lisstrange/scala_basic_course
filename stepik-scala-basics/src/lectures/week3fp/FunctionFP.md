## Функции в ФП
Давайте немного поговорим о том, что же такое функциональное программирование. Если выбрать наиболее краткое из всех доступных определений, то это написание кода с использованием только чистых функций и неизменяемых переменных.

 

Чистая функция(pure function) - это когда

результат работы функции зависит только от того, что она получает на вход, и описанного внутри нее алгоритма. А значит, сколько бы раз такая функция ни была вызвана, например, для параметра х - результат ее работы будет неизменным
нет ни чтения, ни записи в файл или любого другого взаимодействия с внешним источником данных (файлом, базой данных, UI)
Примером чистой функции может служить уже известная вам toUpperCase. С другой стороны, System.nanoTime() - это уже не про чистые функции.

 

Так что, когда думаете о функциональном программировании, полезно вспомнить алгебру. Посмотрите на код, ничего не напоминает:
```
  val a = f(x)
  val b = y(a)
  val c = z(b)
```
Такой стиль дает ряд преимуществ: так вы знаете, что а - это всегда f(x), поэтому b можно представить как y(f(x)). Аналогичные действия можно проделать и с c. Так что готовьтесь к тому, что ваш код будет напоминать следующий:
```
  val c = z(y(f(x)))
```

### Функции
Итак, пора начинать мыслить функционально и привыкать к тому, что теперь мы будем:

использовать функции в качестве параметров
возвращать функции из функций
 

Допустим, нам потребовалась функция умножения числа на константу. Вспомнив ООП, где мы работаем с классами и их экземплярами, можно сообразить вот такой код:
```
  class Multiplication {
    def multiply(x: Int): Int = x * 2
  }
 ```

Можно пойти еще дальше и, применив обобщенные классы, снять ограничения в использовании типов:
```
  trait Multiplication[A, B] {
    def multiply(x: A): B
  }
```

Но и это не будет пределом. Ведь есть еще apply:
```
  trait Multiplication[A, B] {
    def apply(x: A): B
  }
```

Теперь, если объявим переменную res:
```
  val res = new Multiplication[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }
```
Обращаться к ней сможем так, что выглядеть это будет - как обращаение к функции:
```
  println(res(2))
```

Довольно неплохо, правда? Особенно, если учесть, что подобные трейты уже прописаны в Scala за нас, чем можно вовсю пользоватьcя. В нашем распоряжении Function0, Function1, Function2, Function3 и так до Function22 включительно. Числа 0, 1, 2, 3, ..., 22 в конце слова Function - указывают на количество задаваемых параметров.

 

Таким образом, для нашей задачи с одним передаваемым параметром прекрасно подойдет Function1[A, B]
```
  val res = new Function1[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }
```

Соответственно, если захотим передать два параметра, то тут уже пригодится Function2 (в качестве напоминания: третий Int в скобках у Function2 - обозначает тип возвращаемого значения):
```
  val product = new Function2[Int, Int, Int] {
    override def apply(x: Int, y: Int): Int = x * y
  }
  println(product(3, 4) ) // выводит 12
```


## Анонимные функции или лямбда-функции
Взглянем на код еще раз:
```
  val product = new Function2[Int, Int, Int] {
    override def apply(x: Int, y: Int): Int = x * y
  }
```
Если навести мышку и посмотреть на тип переменной product, то мы увидим (Int, Int) => Int. А это, кстати, является еще одним синтаксическим сахаром:


![image](https://user-images.githubusercontent.com/47192124/169691956-bddf09b8-2a4d-4c44-b902-79c9163df25b.png)

 

Способов написания кода с использованием анонимных функций (или лямбда-выражений) несколько (и все они дадут один и тот же результат):

![image](https://user-images.githubusercontent.com/47192124/169691958-ef4d06f9-cddc-4d5a-a0bd-05b02d4ce18b.png)


Самый последний метод позволяет компилятору самому подставить переменные вместо _. Удобно, конечно, но тут будьте внимательны - не напутайте с передаваемыми переменными.

 ![image](https://user-images.githubusercontent.com/47192124/169691963-4d7dd9f0-b0d1-4f2f-b70a-6c7aac9ea084.png)

Для закрепления - приложим еще одну иллюстрацию для функции с одним параметром. Обратите внимание на код под номером 4 - это еще один способ написания анонимных функций - в фигурых скобках (мы бы не советовали отдавать предпочтение именно этому способу, достаточно просто знать, что и такое есть)

П.С. Более внятно и подробно: https://www.youtube.com/watch?v=7k1FSTaPJts&list=PLtX3ATr7wKKlhdq3unb2TiyoOcT8jVUGC&index=6





