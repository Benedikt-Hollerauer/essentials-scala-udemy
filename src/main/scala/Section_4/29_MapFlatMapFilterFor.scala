package Section_4

object MapFlatMapFilterFor extends App
{
    val list: List[Int] = List(1, 2, 3)
    println(list)

    // print all combinations between two lists

    val numbers: List[Int] = List(1, 2, 3, 4)
    val chars: List[Char] = List('a', 'b', 'c', 'd')

    val concatenation: List[String] = chars.flatMap(char => numbers.map(number => char + "" + number))
    println(concatenation)

    // foreach ist das gleiche wie map, bloß das es unit returned also einen siteeffekt
    list.foreach(println)

    val forConcatenation = for{
        n <- numbers
        c <- chars
    } yield c + "" + n

    println(forConcatenation)

    /**
     *  Exercise:
     *  1. MyList supports for comprehensions?
     *
     *  - hier muss man die type signitur ansehen
     *
     *  2. A small collection of at most ONE element - Maybe[+T]
     *      - map
     *      - flatMap
     *      - filter
     */

    // 2

    abstract class MayBe[+T]
    {
        def map[B](f: T => B): MayBe[B]
        def flatMap[B](f: T => MayBe[B]): MayBe[B]
        def filter(f: T => Boolean): MayBe[T]
    }

    case object MayBeNot extends MayBe[Nothing]
    {
        def map[B](f: Nothing => B): MayBe[B] = MayBeNot
        def flatMap[B](f: Nothing => MayBe[B]): MayBe[B] = MayBeNot
        def filter(f: Nothing => Boolean): MayBe[Nothing] = MayBeNot
    }

    case class MayBeYes[T](value: T) extends MayBe[T]
    {
        def map[B](f: T => B): MayBe[B] = MayBeYes(f(value))
        def flatMap[B](f: T => MayBe[B]): MayBe[B] = f(value)
        def filter(f: T => Boolean): MayBe[T] =
        {
            if(f(value)) this
            else MayBeNot
        }
    }
}