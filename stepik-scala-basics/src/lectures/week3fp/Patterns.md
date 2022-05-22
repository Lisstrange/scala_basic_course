## Шаблоны (Patterns)
Шаблоны применяются тогда, когда для конкретных значений требуется определить свой вариант действия программы (можно провести параллели со switch-case из других языков). Выглядит это так:
```
  val someVal = 3

  val description = someVal match {
    case 1 => "action 1"
    case 2 => "action 2"
    case 3 => "action three"
    case _ => "default action"
  }

  println(description) // action three
```
Рядом с case мы задаем шаблоны, которые сравниваем со значением переменной someVal. А после => указываем желаемый результат для заданного случая.

Символ _ обычно описывает ситуацию, когда совпадений среди шаблонов не найдено т.е. результат по умолчанию.

## Виды шаблонов
В качестве шаблонов можно использовать:

константы
```
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "число"
    case "Scala" => "строка"
    case true => "булевое значение"
  }
```
Обратите внимание на тип Any. Он потребовался в силу того, что в качестве x в нашем случае может быть использовано и число, и строка, и булевое значение

 

туплы
```
  val aTuple = (5,2)
  val matchATuple = aTuple match {
    case (1, 1) => "полное совпадение"
    case (something, 2) => s"я нашел $something"
  }

  println(matchATuple) // я нашел 5
```
Вложенные туплы также не являются проблемой:
```
  val nestedTuple = (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) => s"тут есть число $v"
  }

  println(matchNestedTuple) // тут есть число 3
 ```

списки
```
  val aStandardList = List(5, 4)
  val listMatching = aStandardList match {
    case List(1, _, _, _) => "список из 4 элементов, где первый элемент равен 1"
    case List(2, _*) => "список произвольной длины, где первый элемент равен 2"
    case List(3, 2, 1) :+ 0 => "список List(3, 2, 1, 0)"
    case 5 :: List(_) => "список, где первым идет число 5 и еще один элемент"
    case _ => "default"
  }
```

типы
```
  val unknown: Any = List(1, 2)
  val typeMatch = unknown match {
    case list: List[Int] => "list of INTs"
    case _ => "default"
  }

  println(typeMatch) // list of INTs
 ```

классы-образцы 
```
  case class Person(name: String, age: Int)

  val bob = Person("Bob", 30)
  val greeting = bob match {
    case Person(n, a) if a < 18 => s"I'm $n and I'm under 18"
    case Person(n, a) if n != "name" => s"I'm $n and I am $a years old"
    case _ => "I have no name"
  }

  println(greeting) // I'm Bob and I am 30 years old
```
### Привязка имен (Name Binding)
Довольно полезная вещь, если требуется как-то обратиться к найденному совпадению.

 

Задается имя совпадения, ставится знак @, после которого прописывается сам шаблон:
```
  val nameBindingMatch = List(6, 2) match {
    case nonEmptyList@List(1, _, _, _) => s"нашли $nonEmptyList"
    case someList@List(6, _*) => s"нашли список $someList"
  }

  println(nameBindingMatch) // нашли список List(6, 2)
```

## Поговорим еще немного о шаблонах. Посмотрите на код:
```
  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of integers"
    case _ => "default case"
  }
  
  println(numbersMatch)
```
Если код запустить, то на экране увидите a list of strings. И это несмотря на то, что на вход был подан целочисленный список.

 

Причина этому - JVM. Компилятор на самом деле видит код без generic-типов. Т.е. для компилятора код выглядит вот так:
```
  val numbersMatch = numbers match {
    case listOfStrings: List => "a list of strings"
    case listOfNumbers: List => "a list of integers"
    case _ => "default case"
  }
```
А так как шаблоны проверяются по порядку - один за другим, то первый же шаблон будет считаться подходящим, и на экране будет a list of strings

Именно этим и объясняется то, что вам выдается предупреждение:

![image](https://user-images.githubusercontent.com/47192124/169692265-2c262386-4586-44f3-84d5-93f6ffe7ade4.png)











