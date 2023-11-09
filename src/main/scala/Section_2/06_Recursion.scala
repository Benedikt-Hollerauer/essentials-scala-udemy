package Section_2

import scala.annotation.tailrec
import scala.jdk.Accumulator

object Recursion extends App
{
    def factorial(n: Int): Int =
    {
        if(n <= 1) 1
        else n * factorial(n - 1)
    }

    /*println(factorial(10))
    println(factorial(50000))*/

    def anotherFactorial(n: BigInt): BigInt =
    {
        @tailrec
        def factorialHelper(x: BigInt, accumulator: BigInt): BigInt =
        {
            if(x <= 1) accumulator
            else factorialHelper(x - 1, x * accumulator)                                // TAIL-RECURSION = use recursive call as LAST expression
        }

        factorialHelper(n, 1)
    }

    println(anotherFactorial(500000))

    /*println(anotherFactorial(5000))*/

    // Exercises

    /**
     * 1. Concatenate a string n times
     * 2. IsPrime function tail recursive
     * 3. Fibonacci tail recursive
     */

    // 1

    def concatenateTailrec(aString: String, n: Int, accumulator: String): String =
    {
        if(n <= 0) accumulator
        else concatenateTailrec(aString, n - 1, aString + accumulator)
    }

    /*println(concatenateTailrec("hello", 3, ""))*/

    // 2

    def isPrime(maybePrime: Int): Boolean =
    {
        def isPrimeUntil(t: Int, isStillPrime: Boolean): Boolean =
        {
            if(!isStillPrime) false
            else if (t <= 1) true
            else isPrimeUntil(t - 1, maybePrime % t != 0 && isStillPrime)
        }

        isPrimeUntil(maybePrime / 2, true)
    }

    // 3

    def fibonacci(n: Int): Int =
    {
        def fibonacciTailrec(i: Int, lastNumber: Int, currentNumber: Int): Int =
        {
            if (i >= n) lastNumber
            else fibonacciTailrec(i + 1, lastNumber + currentNumber, lastNumber)
        }

        if (n <= 2) 1
        else fibonacciTailrec(2, 1, 1)
    }

    /*println(fibonacci(8))*/
}