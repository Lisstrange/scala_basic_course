Коллекции (Collections)
Пора познакомиться с основными коллекциями, доступными в Scala.

В нашем распоряжении:

Set ( т.е. без дубликатов или повторяющихся элементов)
Seq (т.е. у каждого элемента свой индекс, например - Vector, Range, List, Array)
Map (т.е. пары ключ-значение) 
 
![image](https://user-images.githubusercontent.com/47192124/169692060-ad2d9030-56fd-427a-ba12-6d219cdde306.png)

![image](https://user-images.githubusercontent.com/47192124/169692063-963f3c4e-aac6-41a4-8127-fd09174ac45b.png)

 ![image](https://user-images.githubusercontent.com/47192124/169692067-455e892d-b9b4-41e4-a166-66ceb49bba55.png)

## Коллекции (Collections)
Array

![image](https://user-images.githubusercontent.com/47192124/169692074-450f3a03-7dd4-4fcf-8930-bef1f1e13a92.png)

Tuple

![image](https://user-images.githubusercontent.com/47192124/169692076-6c3569ca-a2aa-4a21-bab6-adfd381159fc.png)

Range

![image](https://user-images.githubusercontent.com/47192124/169692081-f8f94663-9212-4bff-ac20-dad8fc2a707e.png)


 ## foreach
 
 ![image](https://user-images.githubusercontent.com/47192124/169692093-0888f179-702c-47f5-a3cc-17e1628c981d.png)

## map, flatMap, filter
Смысл map заключается в том, что заданная функция применяется к каждому элементу списка.

flatMap очень похож на map, только он преобразует каждый элемент в целый список элементов и выполняет действия уже с ними, а потом результат собирает в одно целое.

Смотрите на пример:

  val fruits = List("apple", "banana")
  
  val mapResult = fruits.map(_.toUpperCase)
  val flatResult = fruits.flatMap(_.toUpperCase)
  
  println(mapResult) // List(APPLE, BANANA)
  println(flatResult) // List(A, P, P, L, E, B, A, N, A, N, A)
 

Именно из-за того, как работает flatMap, если нам требуется проставить точку после каждого символа строки и на выходе получить модифицированную строку, использовать придется именно его.

  val s = "Hello"
  val newStr: String = s.flatMap(c => (c + "."))
  println(newStr) // H.e.l.l.o.
map тоже сработает, только вернет уже не строку

  println(s.map(c => (c + "."))) // ArraySeq(H., e., l., l., o.)
 

Комбинируя map и flatMap, мы получаем возможность пройтись по списку. Альтернативой является применение for:

![image](https://user-images.githubusercontent.com/47192124/169692103-86204fc9-dd29-4918-8db1-8731bb2ced1c.png)




