package Section_3

object CaseClasses extends App
{
    case class Person(name: String, age: Int)

    /**
     *  1. class parameters are fields
     *  2. sensible toString
     *  3. equals and hashCode implemented by default
     *  4. case classes have handy copy methods
     *  5. case classes have companion objects
     *  6. case classes are serializable
     *  7. case classes have extractor patterns = case classes can be used in PATTERN MATCHING ( ich glaube mit unapply ist das gemeint )
     */

    // 1
    val jim: Person = new Person("Jim", 34)
    println(jim.name)

    // 2
    // println(instance) = println(instance.toString) <- syntactic sugar
    println(jim)

    // 3
    val jim2: Person = new Person("Jim", 34)
    println(jim == jim2)

    // 4
    val jim3: Person = jim.copy(age = 45)
    println(jim3)

    // 5
    val thePerson = Person
    val mary = Person("Mary", 23)
}