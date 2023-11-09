package Section_3

import scala.language.postfixOps

object MethodNotations extends App
{
    class Person(val name: String, favouriteMovie: String, val age: Int = 0)
    {
        def likes(movie: String): Boolean = movie == favouriteMovie
        def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
        def unary_! : String = s"$name, what the heck?"
        def isAlive: Boolean = true
        def apply(): String = s"Hi, my name is $name and I like $favouriteMovie"

        // Exercises

        def +(nickname: String): Person = new Person(s"$name ($nickname)", favouriteMovie)
        def unary_+ : Person = new Person(name, favouriteMovie, age + 1)
        def learns(toBeLearned: String): String = s"$name learns $toBeLearned"
        def learnsScala: String = learns("Scala")
        def apply(howManyWatches: Int): Unit = println(s"$name watched $favouriteMovie $howManyWatches times.")
    }

    val mary: Person = new Person("Mary", "Inception")

    /*
        1.  Overload the + operator
            mary + "the rockstar" => new person "Mary (the rockstar)"
        2.  Add an age to the Person class
            Add a unary + operator => new person with the age + 1
            +mary => mary with the age incrementer
        3.  Add a "learns" method in the Person class => "Mary learns Scala"
            Add a learnsScala method, calls learns method with "Scala".
            Use it in postfix notation.
        4.  Overload the apply method
            mary.apply(2) => "Mary watched Inception 2 times"
    */

    mary + "the Rockstar"
    mary.+("the Rockstar")
    +mary
    mary.learns("the JVM")
    mary(2)
    mary learnsScala
}