package Section_4

object WhatsAFunction extends App
{
    /*val doubler = new MyFunction[Int, Int]
    {
        override def apply(element: Int): Int = element * 2
    }

    println(doubler(2))*/

    def concatenate: (String, String) => String = new ((String, String) => String)
    {
        override def apply(a: String, b: String): String = a + b
    }

    println(concatenate("Hello, ", "Scala"))

    // type Function1[Int, Function1[Int, Int]]

    val specialFunction: (Int) => ((Int) => Int) = new ((Int) => ((Int) => Int))  // this is a curried function
    {
        override def apply(x: Int): Int => Int = new Function[Int, Int]
        {
            override def apply(y: Int): Int = x + y
        }
    }

    val adder = specialFunction(3)
    println(adder(2))
}