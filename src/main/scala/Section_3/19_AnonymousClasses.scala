package Section_3

object AnonymousClasses extends App
{
    abstract class Animal
    {
        def eat: Unit
    }

    val funnyAnimal: Animal = new Animal
    {
        override def eat: Unit = println("ja moin")
    }

    /**
     *  ist das selbe wie:
     *  ( also das untere macht der Compiler automatisch )
     *
     *  class AnonymousClasses$$anon$1 extends Animal
     *  {
     *      override der eat: Unit = println("ja moin")
     *  }
     */

    println(funnyAnimal.getClass)

    /**
     *  Regeln:
     *
     *  1. man muss die constructor argumente immer mit übergeben
     *  2. man muss alle abstrakten methoden ( und felder ) implementieren
     */
}