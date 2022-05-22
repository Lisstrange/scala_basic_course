package lectures.week2oop

object Inheritance extends App {

  class Person {
    def greet: String = "Hello"
  }

  class Student extends Person

  val student = new Student
  println(student.greet)


//  protected vs private
  //При наследовании у нас появляется доступ ко всем полям и методам родительского класса, которые не являются private.
  //
  //Ключевое слово private, написанное перед методом и/или переменной, делает этот метод и/или переменную доступным только для того класса,
  // в котором он и/или она были описаны:
//  protected работает немного по другому, делая отмеченные поля и методы доступными для класса и его подкласса, но недоступными вне их тел


//  ЗАДАЧА
//  Какой код необходимо прописать перед // пропущенный код, чтобы все работало и This is pizza успешно выводилась на экран:
  class Pizza private {
    override def toString = "This is pizza"
  }

//  Чтобы избежать переписывания метода -> можно написать в final def  ..
  // Чтобы избежать наследование класса , нужно писать final class
//  3. использование ключевого слова sealed для класса
//  (это более мягкая версия final, поэтому допускается extends в текущем файле, но воспрещается вне этого файла)
  object Pizza {
    val pizza = new Pizza

    def getInstance: Pizza = {
      pizza
    }
  }

  object TestPizza extends App {

    val pizza = Pizza.getInstance

    println(pizza)
    

  }
///////////////////////////
  val eddy = new Teenager(name = "Eddy", age = 10, id=66)

  println(eddy.greet)

//  Если мы хотим унаследоваться от родительского класса, мы должны передавать все параметры, которые требуются для этого класса. Пример:

//Либо можно выкрутиться через вспомогательный конструктор def this (вспоминаете начало модуля?).
// И теперь компилятор будет находить конструктор класса Person, который не требует никаких параметров, и все будет работать:
//  Метод this будет использовать дефолтные параметры , если они не определены
//  Или можно указать дефолтные параметры класса: Приоритет такой:
//  Передаваемые параметры -> def this -> Дефолтные параметры
  class Human(name: String = "Unknow", age: Int = 100) {
    def greet: String = s"Hi  ${name}"

  def this() = this("Unknow Person", 0)

}
  class Teenager(name: String, age:Int,id:Int) extends Human(name, age) {
    // Мы должны передавать входные параметры для родительского класса!!!
    println(greet)
  }


}




object task1 extends App {
//  Ваша задача - реализовать два класса: Button и RoundedButton.
  //
  //Для класса Button
  //
  //предусмотрите возможность указания label типа String при создании экземпляра класса
  //пропишите метод def click(): String, возвращающий строку, в которой указан label, заданный при создании кнопки:  button -label- has been clicked
  //
  //
  //Для RoundedButton
  //
  //предусмотрите наледование от Button
  //реализуйте метод click, который похож на родительский метод click, но в отличие от него содержит слово rounded перед button: rounded button -label- has been clicked
  //
  //
  //Учтите, что в коде задан еще один класс: TestButton. И выглядит этот класс следующим образом: class TestButton extends Button. Для этого класса метод click всегда выводит test button -test- has been clicked
  //
  //Убедитесь, что вы написали весь необходимый код, чтобы такое задание класса TestButton было возможно.
  //
  //
  //
  //Примечание:
  //
  //постарайтесь использовать super в коде
  //Sample Input:
  //
  //Sample Output:
  //
  //button -save- has been clicked

  val task_1 = new RoundedButton("open")

  class Button (label: String) {

    def click(): String = s"button -${label}- has been clicked"


  }

  class RoundedButton(label: String) extends Button(label) {

    override def click(): String = s"rounded ${super.click}"
// Метод super - вызывает функцию родительского класса
    println(click())


  }

}

object Polymorphism extends App {
//                    Полиморфизм (polymorphism)

  //Полиформизм - это подход при котором в переменной, имеющей тип родителя, допускается хранение ссылки на его потомка.
  // Соответственно допускается использование потомка там где по сигнатуре ожидается родитель,
  // причём в случае "подмены" при попытке вызова какого - либо метода у родителя будет вызван метод потомка.
  // Таким образом полиморфизм позволяет абстрагироваться от конкретных реализаций методов.


  //Полиморфизм подразумевает замену типов. Смотрим пример:


  class Person(name: String, age: Int) {
    val gender = "n/a"

    def greet : String = s"Hello"
    def this() = this("UnknownPerson", 0)
  }

  class Student(name: String, age: Int, id: Int, studentGender: String) extends Person {
    override val gender: String = studentGender
    override def greet: String = s"${super.greet}, $name"
  }

  val student = new Student("James", 24, 1, "m")
  println(student.greet) // Hello, James
  println(student.gender) // male

  // Объявляем переменную с типом Person но вызвали Student
  val aPerson: Person = new Student("Alice", 24, 2, "f")
  println(aPerson.greet) // Hello, Alice
  // Сработает переопределённый метод greet

//  Хотя и объявили переменную aPerson с типом Person, но использовать она будет средства Student, т.к. является экземпляром класса Student
  //  - полиморфизм в действии.
}


object abstract_classes extends App{


//  Абстрактные классы (abstract classes)
  //Бывают ситуации, когда небходимо в классе задать только поле или метод, а имплементацию прописывать в подклассах, подстраивая ее под каждый конкретный случай.
  //В абстрактном классе абстрактные поля мы оставляем пустыми, а абстрактные методы без тела.
  // Это значит, что мы не можем создать экземпляр абстрактного класса, пока абстрактные методы и поля не прописаны должным образом.

  abstract class BaseDataSource(dataSourceName: String) {
    def save: String = {
      s"Save method implemented"
    }

    def delete: String = {
      s"Delete method implemented"
    }
    val user : String
    def connect: String
  }

  class PublicDataSource(ds: String) extends BaseDataSource(ds) {

    val user = "PublicUser"
    override def connect : String = {   // ключевое слово override можно опустить
       s"Public Data Source, no password needed"
    }
  }

  //  Анонимные классы (Anonymous classes)
  //Мы отметили, что мы не можем создать экземпляр абстрактного класса,
  // пока абстрактные методы и поля не прописаны должным образом.
  // Но тогда что же получается, если написать вот так, избежав создания подкласса:
  val someSource = new BaseDataSource("someDS") {
    override val user: String = "someSourceUser"

    override def connect: String = "someSource connection"
  }

  println(someSource.getClass) //class lectures.week2oop.abstract_classes$$anon$1
}

object Traits extends App {
  //                                    Трейты (traits)
  //Очень похожи на абстрактные классы. Как и абстрактные классы,  трейты могут включать в себя неабстрактных членов.
  // Но все же трейты - это не абстрактные классы. И причин этому несколько:
  //
                        //трейты не могут задаваться с параметрами
                        //можно указать несколько трейтов для одного класса
                        //трейты описывают конкретное поведение для конкретной ситуации

//  Абстрактный класс
  abstract class BaseDataSource(dataSourceName: String) {
    def save: String = {
      s"Save method implemented"
    }

    def delete: String = {
      s"Delete method implemented"
    }
    val user : String
    def connect: String
  }
//  Трейт



}

object TASK extends App {

  trait Configs {
    val ACCOUNT_TYPE_DEFAULT = "free"
    val ACCOUNT_TYPE_PAID = "paid"
    val SUBSCRIPTION_STATUS = "active"
  }

  object Settings {
    case class AccountSettings(
                                email: String,
                                password: String,
                                picture: String)

    case class SubscriptionSettings(
                                     payment: String,
                                     notifications: String,
                                     expiration: String)
  }

  trait Subscriber extends Configs {
    def subscribe(settings: Settings.SubscriptionSettings): Unit = println("subscribed")
  }

  trait Unsubscriber extends Configs {
    def unsubscribe(): Unit = println("unsubscribed")
  }

  abstract class Account(accountID: String, settings: Settings.AccountSettings)
    extends Subscriber with Unsubscriber {
    def info(): Unit
  }

  class FreeAccount(
                     accountID: String,
                     settings: Settings.AccountSettings) extends Account(accountID, settings) {

    override def info(): Unit = println(s"Account Type: $ACCOUNT_TYPE_DEFAULT")
  }

  class PaidAccount(accountID: String, settings: Settings.AccountSettings)
    extends Account(accountID, settings) {

    override def info(): Unit = {
      println(s"Account Type: $ACCOUNT_TYPE_PAID")
      println(s"Subscription Status: $SUBSCRIPTION_STATUS")
    }
  }

}