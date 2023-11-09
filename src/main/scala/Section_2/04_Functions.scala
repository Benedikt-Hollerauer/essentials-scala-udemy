package Section_2

import scala.annotation.tailrec

object Functions extends App
{
    /**
     * 1. A greeting function ( name, age ) => "Hi, my name is $name and I am $age years old."
     * 2. Factorial function 1 * 2 * 3 * .. * n
     * 3. A Fibonacci function
     *    f(1) = 1
     *    f(2) = 1
     *    f(n) = f(n - 1) + f(n  2)
     * 4. Tests if a number is prime.
     */

    // 1

    def greeting(name: String, age: Int): Unit = println(s"Hi, my name is $name and I am $age years old.")

    greeting("Bene", 18)

    // 2

    def factorial(n: Int): Int =
    {
        if(n <= 0) 1
        else n * factorial(n - 1)
    }

    println(factorial(5))

    // 3

    def fibonacci(n: Int): Int =
    {
        if(n <= 2) 1
        else fibonacci(n - 1) + fibonacci(n - 2)
    }

    println(fibonacci(8))

    // 1 1 2 3 5 8 13 21

    // 4

    def isPrime(maybePrime: Int): Boolean =
    {
        def isPrimeUntil(t: Int): Boolean =
        {
            if(t <= 1) true
            else maybePrime % t != 0 && isPrimeUntil(t - 1)
        }

        isPrimeUntil(maybePrime / 2)
    }

    println(isPrime(3))
}
