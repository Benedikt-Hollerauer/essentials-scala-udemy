package Section_3

import scala.annotation.tailrec
import scala.collection.View.Zip

object Inheritance extends App
{
    /*val evenPredicate = new MyPredicate[Int]
    {
        override def test(value: Int): Boolean =
        {
            if (value % 2 == 0) true
            else false
        }
    }

    val stringToIntTransformer = new MyTransformer[String, Int]
    {
        override def transform(value: String): Int =
        {
            if(value.toInt.getClass == Int) value.toInt
            else 1
        }
    }*/

    abstract class MyList[+A]
    {
        /**
         * methods:
         * - head               <- first element of list
         * - tail               <- remainder of the list
         * - isEmpty: Boolean   <- checks if list is empty
         * - add(int)           <- new list with this element added
         * - toString           <- a string representation of the list
         */

        def head: A
        def tail: MyList[A]
        def isEmpty: Boolean
        def add[B >: A](toBeAdded: B): MyList[B]
        override def toString: String = "[" + printElements + "]"
        def printElements: String

        // Exercises
        def map[B](transformer: (A) => B): MyList[B]
        def flatMap[B](transformer: (A) => MyList[B]): MyList[B]
        def filter(predicate: A => Boolean): MyList[A]

        // concatenation
        def ++[B >: A](list: MyList[B]): MyList[B]

        def foreach(f: A => Unit): Unit
        def sort(compare: (A, A) => Int): MyList[A]
        def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]
        def fold[B](start: B)(operator: (B, A) => B): B
    }

    case object EmptyList extends MyList[Nothing]
    {
        def head: Nothing = throw new NoSuchElementException
        def tail: MyList[Nothing] = throw new NoSuchElementException
        def isEmpty: Boolean = true
        def add[B >: Nothing](toBeAdded: B): MyList[B] = new NonEmptyList(toBeAdded, this)
        def printElements: String = ""

        def map[B](transformer: Nothing => B): MyList[B] = this
        def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = this
        def filter(predicate: Nothing => Boolean): MyList[Nothing] = this

        def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

        def foreach(f: Nothing => Unit): Unit = ()
        def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = this
        def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
        {
            if(!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
            else this
        }
        def fold[B](start: B)(operator: (B, Nothing) => B): B = start
    }

    case class NonEmptyList[+A](h: A, t: MyList[A]) extends MyList[A]
    {
        def head: A = h
        def tail: MyList[A] = t
        def isEmpty: Boolean = false
        def add[B >: A](toBeAdded: B): MyList[B] = NonEmptyList(toBeAdded, this)
        def printElements: String =
        {
            if(t.isEmpty) "" + h
            else s"$h ${t.printElements}"
        }

        def map[B](transformer: A => B): MyList[B] =
        {
            NonEmptyList[B](
                transformer(h),
                t.map(transformer)
            )
        }
        def flatMap[B](transformer: A => MyList[B]): MyList[B] = transformer(h) ++ t.flatMap(transformer)
        def filter(predicate: A => Boolean): MyList[A] =
        {
            if(predicate(h)) NonEmptyList(h, t.filter(predicate))
            else t.filter(predicate)
        }

        def ++[B >: A](list: MyList[B]): MyList[B] = NonEmptyList[B](h, t ++ list)

        def foreach(f: A => Unit): Unit =
        {
            f(h)
            t.foreach(f)
        }
        def sort(compare: (A, A) => Int): MyList[A] =
        {
            def insert(x: A, sortedList: MyList[A]): MyList[A] =
            {
                if(sortedList.isEmpty) NonEmptyList(x, EmptyList)
                else if(compare(x, sortedList.head) <= 0) NonEmptyList(x, sortedList)
                else NonEmptyList(sortedList.head, insert(x, sortedList.tail))
            }
            val sortedTail: MyList[A] = t.sort(compare)
            insert(h, sortedTail)
        }
        def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] =
        {
            if(list.isEmpty) throw new RuntimeException("Lists do not have the same length")
            else NonEmptyList(zip(h, list.head), t.zipWith(list.tail, zip))
        }
        def fold[B](start: B)(operator: (B, A) => B): B = t.fold(operator(start, h))(operator)
    }

    val listOfIntegers: MyList[Int] = NonEmptyList[Int](1, NonEmptyList[Int](2, EmptyList))
    val listOfStrings: MyList[String] = NonEmptyList[String]("Hello", NonEmptyList[String]("Scala", EmptyList))

    println(listOfStrings)
    println(listOfIntegers)
    println(listOfIntegers.map(_ * 2))
    println(listOfIntegers.filter(_ % 2 == 0))
    println(listOfIntegers.flatMap(element => NonEmptyList(element, NonEmptyList(element + 1, EmptyList))))
    println(listOfIntegers ++ listOfIntegers ++ listOfIntegers)

    listOfIntegers.foreach(println)
    println(listOfIntegers.sort((x, y) => y - x))
    println(listOfIntegers.zipWith(listOfStrings, _ + "-" + _))
    println(listOfIntegers.fold(0)(_ + _))

    val forComprehension = for{
        number <- listOfIntegers
        string <- listOfStrings
    } yield number + "" + string

    println(forComprehension)

    // Exercise:

    /**
     *  1. Generic trait MyPredicate[-T] with a method test(T) => Boolean
     *     2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
     *     3. MyList:
     *      - map(transformer) => MyList
     *      - filter(predicate) => MyList
     *      - flatMap(transformer from A to MyList[B]) => MyList[B]
     *
     * class EvenPredicate extends MyPredicate[Int]
     * class StringToIntTransformer extends MyTransformer[String, Int]
     *
     * [1, 2, 3].map(n * 2) = [2, 4, 6]
     * [1, 2, 3, 4].filter(n % 2) = [2, 4]
     * [1, 2, 3].flatMap(n => [n, n + 1]) = [1, 2, 2, 3, 3, 4]
     */
}