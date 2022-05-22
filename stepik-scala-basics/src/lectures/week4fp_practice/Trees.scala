package lectures.week4fp_practice

import lectures.week4fp_practice.Trees.{BinaryTree, Node, TreeEnd}

import scala.::

object Trees extends App {

//   Напомним, что бинарное дерево состоит из узлов, каждый из которых имеет свое значение.
  //
  //В таком дереве каждый узел:
  //
  //либо не имеет потомков вообще (такие узлы называются листьями),
  //либо имеет только одного потомка (правого или левого),
  //либо имеет обоих потомков.
  //Самый верхний узел является корнем дерева


//  Описать структуру данных для дерева можно вот так:

//  Добавим в код метод isEmpty, который определяет наличие узлов, и метод isLeaf, который определяет, является ли узел листом.
//
//  В коде ниже мы прописали метод isEmpty везде, где требуется, и частично внедрили метод isLeaf:


  abstract class BinaryTree[+T] {
    def value: T
    def leftChild: BinaryTree[T]
    def rightChild: BinaryTree[T]

    def isEmpty:  Boolean
    def isLeaf: Boolean
  }

  case class Node[+T](
                       override val value: T,
                       override val leftChild: BinaryTree[T],
                       override val rightChild: BinaryTree[T])
    extends BinaryTree[T] {

    override def isEmpty: Boolean = false
    override def isLeaf: Boolean = ???
  }

  case object TreeEnd extends BinaryTree[Nothing] {
    override def value: Nothing = throw new NoSuchElementException
    override def leftChild: BinaryTree[Nothing] = throw new NoSuchElementException
    override def rightChild: BinaryTree[Nothing] = throw  new NoSuchElementException

    override def isEmpty: Boolean = true
    override def isLeaf: Boolean = false
  }

//  Тогда дерево, представленное на иллюстрации выше, можно задать в коде следующим образом:

  val tree = Node(1,
    Node(2,
      TreeEnd,
      TreeEnd),
    Node(3,
      TreeEnd,
      TreeEnd))

//  Как вы можете видеть, в коде задействованы обобщения, так как планируется создание нескольких деревьев,
  //  одно из которых, например, будет состоять из узлов типа Int, другое же будет, например, состоять из узлов типа String.
  //
  //Кроме того, мы воспользовались ковариантностью +T. Это необходимо для предотвращения нежелательного смешения типов.
  //
  //Напомним, что ковариантность предполагает работу с подтипами.
  // Т.е. если в качестве T использовать Parent, то далее допускается работа с Child, причем Child должен быть подтипом Parent,
  // т.к. работа с супертипами Parent будет запрещена.  Проиллюстрировать это можно на следующем примере:


  class GrandParent
  class Parent extends GrandParent

  class Child extends Parent

  class Family[+T]

  val covariantFamily: Family[Parent] = new Family[Child]
//  val someFamily: Family[Parent] = new Family[GrandParent] // так написать не получится, будет ошибка

//  Соответственно, для работы с супертипами и запрета использования подтипов как раз и существует контравариантность:

  class Family2[-T]

  val contravariantFamily: Family2[Parent] = new Family2[GrandParent]
//  val someFamily: Family2[Parent] = new Family2[Child] // здесь будет ошибка
}

object Task_1 extends App {
//  Следующим шагом будет получение списка всех листьев дерева, написание метода collectLeaves.
  //  Облегчим вам задачу и предоставим вам самостоятельно написать метод collectLeaves только для класса Node:
  abstract class BinaryTree[+T] {
  def value: T
  def leftChild: BinaryTree[T]
  def rightChild: BinaryTree[T]

  def isEmpty:  Boolean
  def isLeaf: Boolean

  def collectLeaves: List[T]
  def hasPath(tree: BinaryTree[Int], target: Int): Boolean
}

  case class Node[+T](
                       override val value: T,
                       override val leftChild: BinaryTree[T],
                       override val rightChild: BinaryTree[T])
    extends BinaryTree[T] {

    override def isEmpty: Boolean = false

    override def isLeaf: Boolean = leftChild.isEmpty && rightChild.isEmpty

    override def collectLeaves: List[T] = {
        def loop(toInspect: List[BinaryTree[T]] = List(this), nodes: List[BinaryTree[T]] = List(this)): List[T] = {
          toInspect match {
            case Nil  => nodes.map(_.value)
            case head :: tail =>
              if (head.isEmpty) loop(toInspect = tail, nodes = nodes)
              else if (head.isLeaf) loop(toInspect = tail, nodes = nodes)
              else loop(toInspect = head.leftChild :: head.rightChild :: tail, nodes =head.leftChild :: head.rightChild ::  nodes)
          }
        }
        loop()
      }



    def hasPath(tree: BinaryTree[Int], target: Int): Boolean = {
      // будем делать метод, который будет собирать все возможные пути, а потом будет искать среди них нужный

      // 1. Проходим по списку путей, возвращаем 2 списка head.leftChild & head.rightChild.
      // Нужно продумать метод, который будет из одного списка возвращать список из двух списков

      // 2. Проходим по всем нашим спискам и считаем суммы. Если в списке сумм есть наше значение - возвращаем true , иначе false






    true}

  // end class
  }

  case object TreeEnd extends BinaryTree[Nothing] {
    override def value: Nothing = throw new NoSuchElementException
    override def leftChild: BinaryTree[Nothing] = throw new NoSuchElementException
    override def rightChild: BinaryTree[Nothing] = throw  new NoSuchElementException

    override def isEmpty: Boolean = true

    override def isLeaf: Boolean = false
    override def collectLeaves: List[Nothing] = List()
    def hasPath(tree: BinaryTree[Int], target: Int): Boolean = false
  }


  val tree = Node("1",
    Node("2",
      TreeEnd,
      TreeEnd),
    Node("3",
      TreeEnd,
      TreeEnd))

  val ListTree: List[BinaryTree[String]] = List(tree)
  // нам нужно написать метод collectLeaves List[BinaryTree[Nothing]]
  // метод ничего не принимает на вход (наверное, он преобразует само дерево , либо мы можем использовать объекты внутри дерева ( value, left/right child)
  // метод возвращает список  value пустых деревьев  isEmpty = True
//  tree.collectLeaves // List(1,2,4,5)

  println()
  println("Start to test tree: ", tree)
  println()


  val base_node = List(tree.leftChild.leftChild)
  println()
  println("base node is ", base_node)
  val head::tail = base_node
  print("Check list with base node: ")
  print(head)
  println()

  if (head.isLeaf) print(head.rightChild ::  head.rightChild :: base_node)
  else None


//  println("get list tree: ", ListTree)
//  print("split list tree, head = ")
//  println( head)
//
//  println(head.leftChild)
//  print(ListTree :: List(head.leftChild))
//  val ListTree2 = (ListTree :: List(head.leftChild))
//  println(ListTree)
//  println(ListTree2)
//  val head2::tail2 = ListTree2
//  println(head2)
//  println(tail2)
//  println(tree.collectLeaves)
  val test_res = List(Node(7,TreeEnd,TreeEnd), Node(6,TreeEnd,TreeEnd), Node(8,TreeEnd,TreeEnd), Node(4,TreeEnd,TreeEnd))

  val first_res_node = test_res(0)
  println(first_res_node)
  println(first_res_node.value)
  val correct_res = test_res.map(_.value).sorted
  println(tree.collectLeaves)

  println(List(tree))

  def getRoad(list_tree: List[List[BinaryTree[String]]], answers: List[List[BinaryTree[String]]] = List() ): List[List[BinaryTree[String]]] = {
    list_tree match {
      case Nil => answers
      case first_el::others =>
        val head::tail = first_el
        if (head.isLeaf) getRoad(list_tree=others, answers = first_el :: answers)
        else if (head.isEmpty) getRoad(list_tree=others, answers = answers)
        else getRoad(list_tree= List(head.rightChild :: first_el) ++ others ++ List(head.leftChild :: first_el), answers = answers)
    }
  }

  var list_tree = List(tree.leftChild::List(tree), tree.rightChild::List(tree))
  println(List(tree))
  println("Start merhod")
  val tree2 = Node("1",
    Node("12",
      Node("4",
        TreeEnd,
        Node("8",
          TreeEnd,
          TreeEnd)),
      Node("5",
        TreeEnd,
        TreeEnd)),
    Node("3",
      Node("6",
        TreeEnd,
        TreeEnd),
      Node("7",
        TreeEnd,
        TreeEnd))
  )
  val full_roads = getRoad(list_tree= List(List(tree2)))

//  println(full_roads.filter(_.map(_.value.toInt).sum == 25) )
//  println(full_roads.map(_.map(_.value).sum).contains(1))
  val filtered_res = full_roads.filter(_.map(_.value.toInt).sum == 25 )

  println(filtered_res.map(_.map(_.value).sorted ))
}

