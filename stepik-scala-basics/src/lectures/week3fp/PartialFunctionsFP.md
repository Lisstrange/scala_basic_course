## Частичные функции (Partial functions)
С функций мы этот модуль начали, функциями и закончим.

 

Часто бывает так, что требуется ограничить значения, которые можно подавать на вход функции. Конечно, можно выкрутиться через шаблоны. Так, например, мы допускаем только значения mon, fri, sun:
```
  val whatToDo = (d: String) => d match {
    case "mon" => "Work!"
    case "fri" => "Party Time"
    case "sun" => "Relax a little"
  }
 ```

Однако есть и другое решение, которое основано на применении PartialFunction.
```
  val aPartialFunction: PartialFunction[String, String] = {
    case "mon" => "Work!"
    case "fri" => "Party Time"
    case "sun" => "Relax a little"
  } 
 ```

Если запустить функцию, то результат будет такой:
```
  println(aPartialFunction("sun")) // Relax a little
  println(aPartialFunction("sat")) // MatchError
```
Для недопустимого значения получаем MatchError - намек, что частичные функции основаны на совпадении шаблонов.

Заметьте, что, как и в случае Function, последний из указываемых в квадратных скобках типов обозначает тип возвращаемого значения.

### Проверка аргумента
Можно заранее узнать, допустимо ли использовать аргумент в функции или нет. В этом нам поможет isDefined:
```
  val aPartialFunction: PartialFunction[String, String] = {
    case "mon" => "Work!"
    case "fri" => "Party Time"
    case "sun" => "Relax a little"
  } 

  println(aPartialFunction.isDefinedAt("tue")) // false
```

## Частичные функции и orElse
Объединить несколько функций в цепь нам поможет orElse:

![image](https://user-images.githubusercontent.com/47192124/169692300-d1599c28-43d1-498e-840c-e8798b7f7cc7.png)

## Лифтинг
Можно сказать, что лифтинг позволяет поднять частичную функцию на следующий уровень. После lift - функция будет возвращать значения типа Option (т.е. решается проблема MatchError)

 
```
  val aPartialFunction: PartialFunction[String, String] = {
    case "mon" => "Work!"
    case "fri" => "Party Time"
    case "sun" => "Relax a little"
  } 


  val lifted = aPartialFunction.lift // теперь на выходе имеем тип Option[String]
  
  println(lifted("fri")) // Some(Party Time)
  println(lifted("thu")) // None
```
