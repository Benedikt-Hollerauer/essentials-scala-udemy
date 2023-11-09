package Section_4

import scala.util.Random

object Sequences extends App
{
    val aSequence = Seq(1, 2, 3, 4, 5)

    println(aSequence)
    println(aSequence.reverse)
    println(aSequence(2))
    println(aSequence ++ Seq(6, 7, 8, 9, 10))
    println(aSequence.sorted)

    // Ranges
    val aRange: Seq[Int] = 1 to 10

    aRange.foreach(println)

    // Lists
    val aList: List[Int] = List(1, 2, 3, 4, 5)

    val prependedList: List[Int] = 42 +: aList :+ 89
    println(prependedList)

    val apples5 = List.fill(5)("apple")
    println(apples5)
    println(aList.mkString("-|-"))

    // Arrays
    val numbers = Array(1, 2, 3, 4, 5)
    val fiveElements = Array.ofDim[Int](5)

    println(fiveElements)
    fiveElements.foreach(println)

    // mutation
    numbers(2) = 0
    println(numbers.mkString("-"))

    // arrays abd seq
    val numbersSeq: Seq[Int] = numbers
    println(numbersSeq)

    // vectors
    val vector: Vector[Int] = Vector(1, 2, 3)
    println(vector)

    // vectors vs lists
    val maxRuns = 1000
    val maxCapacity = 100000000

    def getWriteTime(collection: Seq[Int]): Double =
    {
        val random = new Random
        val times = for{
            iteration <- 1 to maxRuns
        } yield{
            val currentTime = System.nanoTime()
            collection.updated(random.nextInt(maxCapacity), random.nextInt())
            System.nanoTime() - currentTime
        }
        times.sum * 1.0 / maxRuns
    }

    // advantage    -> keeps reference to tail
    // disadvantage -> updating an element in the middle takes long
    val numbersList = (1 to maxCapacity).toList
    // advantage    -> depth of the tree is small
    // disadvantage -> needs to replace an entire 32-element chunk
    val numbersVector = (1 to maxCapacity).toVector

    println(getWriteTime(numbersList))
    println(getWriteTime(numbersVector))
}