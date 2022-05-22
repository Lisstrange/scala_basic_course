## Синтаксический сахар (Syntactic Sugar)
Синтаксический сахар - это альтернативный способ написания кода, который больше напоминает естественный язык. Поэтому может быть более приятен человеческому глазу.

Приведенные далее примеры вам все наглядно продемонстрируют.

### Инфиксная нотация (Infix notation)
Подходит только для методов с одним параметром 

 

Создадим класс Person, в котором пропишем метод worksAs:
```
  class Person(val name: String, occupation: String) {
    def worksAs(jobName: String): Boolean = jobName == occupation
  }
``` 

Далее создадим экземпляр класса Person:
```
  val bob = new Person("Bob", "Developer")
``` 

Если мы захотим для Боба вызвать метод worksAs, то можем это сделать привычным нам способом - через точечную нотацию:
```
  println(bob.worksAs("Developer")) // true
```

Но так как worksAs принимает на вход всего один аргумент, то вызов этого метода мы можем переписать в инфиксной нотации:
```
  println(bob worksAs "Developer") // true
```
Результат вызова функции от этого нисколько не изменился, но посмотрите, насколько код стал напоминать обычное предложение. А ведь мы всего-то избавились от точки и скобок, обратившись к пробелам.

## Постфиксная нотация (Postfix notation)
Подходит только для методов без параметров 

 

Продолжим работать с созданным ранее классом Person. Добавим в него метод speaksEnglish:
```
  class Person(val name: String, occupation: String) {
    def worksAs(jobName: String): Boolean = jobName == occupation
    def speaksEnglish: Boolean = true
  }

  val bob = new Person("Bob", "Developer")
```
 

Постфиксная нотация аналогична инфиксной, просто используется для методов без параметров. Так что никаких сюрпризов в том, как это работает, для вас быть не должно:
```
  println(bob.speaksEnglish) // точечная нотация
  println(bob speaksEnglish) // постфиксная нотация
```
Примечание: на практике инфиксная и постфиксная нотации применяются довольно редко. И предпочтение отдается точечной нотации, чтобы не вводить в заблуждение коллег. Кроме того, в версиях Scala, начиная с 2.13, постепенно уходят от постфиксных нотаций, что требует дополнительного импортирования import scala.language.postfixOps 

## Операторы
Прежде чем показать последнюю из трех нотаций, немного затронем операторы.

 

Дело в том, что операторы в Scala на самом деле являются методами. А это значит, что к ним тоже вполне можно обратиться через точку:
```
  println(1 + 2) // обычное обращение
  println(1.+(2)) // обращение через точку
```

      2. А еще операторы можно использовать в качестве имени метода.

Давайте расширим уже известный нам класс Person и добавим в него метод &
```
  class Person(val name: String, occupation: String) {
    def worksAs(jobName: String): Boolean = jobName == occupation
    def speaksEnglish: Boolean = true

    def &(person: Person): String = s"${this.name} and ${person.name} are colleagues"
  }

  val bob = new Person("Bob", "Developer")
```

Раз у метода & всего один аргумент - нам становится доступна инфиксная нотация:
```
  val alice = new Person("Alice", "Data Engineer")
  
  println(bob.&(alice)) // точечная нотация
  println(bob & alice) // инфиксная нотация
```

## Префиксная нотация (Prefix notation)
Думаем, вам она знакома:
```
  val x = -1 
``` 

Но если бы на этом было все - было бы скучно. Для записи выше существует эквивалент с использованием unary_
```
val x = 1.unary_-
```
Префикс unary_ подходит только для + - ~ ! 

 

Оператор с unary_ также можно использовать в качестве имени функции:
```
  class Person(val name: String, occupation: String) {
    def worksAs(jobName: String): Boolean = jobName == occupation
    def speaksEnglish: Boolean = true
    def &(person: Person): String = s"${this.name} and ${person.name} are colleagues"

    def unary_! : String = s"$name is not real"
  }

  val bob = new Person("Bob", "Developer")
```
Примечание: у функции unary_! нет аргументов, поэтому был поставлен пробел и только после него шло двоеточие и дописывался тип возвращаемого значения. Это было сделано для того, чтобы компилятор не воспринимал unary_!: как имя функции, а использовал в качестве имени только unary_! - без двоеточия

 

Теперь вызвать unary_! мы можем тремя способами:
```
  println(!bob) // префиксная нотация
  println(bob.unary_!) // точечная нотация
  println(bob unary_!) // постфиксная нотация
```

## Метод apply
А вот этот материал вам нужно точно запомнить 

 

Для начала - добавим apply в класс (то, что метод будет делать - не суть важно, главное сейчас - чтобы apply просто был):
```
  class Person(val name: String, occupation: String) {
    def worksAs(jobName: String): Boolean = jobName == occupation
    def speaksEnglish: Boolean = true
    def &(person: Person): String = s"${this.name} and ${person.name} are colleagues"
    def unary_! : String = s"$name is not real"

    def apply(): String = s"$name works as a $occupation"
  }


  val bob = new Person("Bob", "Developer")
 
```
И опять у нас на выбор два способа вызова apply:
```
  println(bob.apply())
  println(bob()) // используется наболее часто
```
Причем второй способ наиболее популярен. Так что запомните: как только вы видите какой-то объект класса, который подозрительно похож на функцию, знайте - все дело в apply.




