package Section_3

import scala.*

object Exceptions extends App
{
    val exception: String = null

    /*println(exception.length)*/

    lazy val aWierdValue: Nothing = throw new NullPointerException

    // throwable classes extend the Throwable class.
    // Exception and Error are the major Throwable subtypes

    // Exception <- something that went wrong with the programm f.e. NullPointerException
    // Error <- something that went wrong with the systen f.e. stackoverflow error

    // catch exceptions#
    def getInt(withExceptions: Boolean): Int =
    {
        if(withExceptions) throw new RuntimeException("No Int for you!")
        else 42
    }

    val potentialFail: AnyVal = try{
        getInt(true)
    } catch {
        case e: RuntimeException => println(42)
    } finally {
        println("finally")      // only use finally for side effects
    }

    // own exception

    class MyException extends Exception

    val exception2 = new MyException

    /*throw exception2*/

    /**
     *  Exercise
     *
     *  1. Crash your program with an OutOfMemoryError
     *  2. Crash with StackOverflowError
     *  3. PocketCalculator
     *      - add(x, y)
     *      - subtract(x, y)
     *      - multiply(x, y)
     *      - divide(x, y)
     *
     *      Throw:
     *          - OverflowException if add(x, y) exceeds Int.MAX_VALUE
     *          - UnderflowException if subtract(x, y) exceeds Int.MIN_VALUE
     *          - MathCalculationException for division by 0
     */

    // 1

    /*val array = Array.ofDim[Int](Int.MaxValue)*/

    // 2

    /*def infinite: Int = 1 + infinite
    val noLimit = infinite*/

    // 3

    class OverflowException extends RuntimeException
    class UnderflowException extends RuntimeException


    class MathCalculationException extends RuntimeException("Division by zero")

    object PocketCalculator
    {
        def add(x: Int, y: Int): Int =
        {
            val result: Int = x + y
            if(x > 0 && y > 0 && result < 0) throw new OverflowException
            else if(x < 0 && y < 0 && result > 0) throw new UnderflowException
            else result
        }

        def subtract(x: Int, y: Int): Int =
        {
            val result: Int = x - y
            if (x > 0 && y > 0 && result < 0) throw new OverflowException
            else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
            else result
        }

        def multiply(x: Int, y: Int): Int =
        {
            val result = x * y
            if (x > 0 && y > 0 && result < 0) throw new OverflowException
            else if (x < 0 && y < 0 && result < 0) throw new OverflowException
            else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
            else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
            else result
        }

        def divide(x: Int, y: Int): Int =
        {
            if (y == 0) throw new MathCalculationException
            else x / y
        }
    }

    /*println(PocketCalculator.divide(2, 0))*/
    println(PocketCalculator.add(2, 0))
    println(PocketCalculator.subtract(2, 0))
    println(PocketCalculator.multiply(2, 0))
}