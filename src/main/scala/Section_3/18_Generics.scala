package Section_3

object Generics extends App
{
    class MyList[+A]
    {
        // use the type A
        def add[B >: A](element: B): MyList[B] = ???    // das heißt, das wir eine MyList[Animals] zurückgeben

        /** Explanation
         *
         *  A = Cat
         *  B = Dog == Animal <- also B ist ein Animal
         */
    }

    class MyMap[Key, Value]

    val listOfIntegers = new MyList[Int]
    val listOfStrings = new MyList[String]

    // generic Method

    object MyList
    {
        def empty[A]: MyList[A] = ???
    }

    val emptyListOfIntegers = MyList.empty[Int]

    // variance problem

    class Animal
    class Cat extends Animal
    class Dog extends Animal

    // 1. yes List[Cat] extends List[Animal] <- COVARIANCE !!!!!!!

    class CovariantList[+A]
    val animalList: CovariantList[Animal] = new CovariantList[Cat]

    // 2. No <- INVARIANCE

    class InvariantList[A]
    val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

    // 3. Hell, no! <- CONTRAVARIANCE

    class Trainer[-A]
    val trainer: Trainer[Cat] = new Trainer[Animal]

    // bounded types

    class Cage[A <: Animal](animal: A)

    val cage = new Cage(new Dog)

    class Car
    // val newCage = new Cage(new Car) // <- funktioniert nicht, weil Car kein Animal oder ein Supertype von Animal ist!

    // Exercise

    // expand MyList to be generic
}