# Особенность вызова функций в скала.

Вызов по имени(call-by-name) vs Вызов по значению(call-by-value)
Чтобы увидеть разницу между этими двумя вызовами (кроме того, что рядом с параметром, вызываемым по имени, печатается знак => )давайте поработаем с кодом:
```
  def callByValue(x: Long): Unit = {
    println(s"call by value: x1 = $x")
    println(s"call by value: x2 = $x")
  }

  def callByName(x: => Long): Unit = {  
    println(s"call by name: x1 = $x")
    println(s"call by name: x2 = $x")
  }

  callByValue(System.nanoTime())
  callByName(System.nanoTime())
Note: System.nanoTime() возвращает время выполнения в наносекундах.
```
 

А теперь непосредственно результат выполнения:

![image](https://user-images.githubusercontent.com/47192124/169689403-536b5cab-fd0a-4acd-aa1e-453907237942.png)


Если для callByValue на экран выводятся два одинаковых значения, то для callByName значения будут разными.

 

Это объясняется разницей в подходах к вычислению значений параметров.

Вызов по значению подразумевает вычисление значения переданного выражения перед вызовом функции.
          Преимущество: значение вычисляется только один раз.

Вызов по имени подразумевает вычисление значения выражения в момент его вызова в функции
          Преимущество: значение не вычисляется, если не используется в теле функции.

 

Смотрите: для callByValue мы вычислили значение System.nanoTime() и подставили это значение в функцию. Однако для callByName предварительных вычислений не делалось, и значение высчитывалось уже непосредственно в функции.


![image](https://user-images.githubusercontent.com/47192124/169689415-d86d2f56-3ed9-4e3c-b9c4-856070d9f3db.png)

Примеры:
```
  def someFunc(): Int = 2 * someFunc() + 1
  def callSomeFunc(x: Int, y: => Int) = println(x)

  callSomeFunc(someFunc(), 1)  // Выводит ошибку StackOverflowError
```
```
  def someFunc(): Int = 2 * someFunc() + 1
  def callSomeFunc(x: Int, y: => Int) = println(x)

  callSomeFunc(1, someFunc()) // Получим значение 1
```
