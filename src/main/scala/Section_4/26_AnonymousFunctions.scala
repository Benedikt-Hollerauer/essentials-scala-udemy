package Section_4

object AnonymousFunctions extends App
{
    // anonymous function ( LAMDA )
    val doubler: Int => Int = x => x * 2 // hier wird automatisch eine Function1 erstellt!!!

    // meherere parameter müssen i eine klamma gepackt werden
    val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

    println(adder(1, 1))

    // keine parameter
    val justDoSomething: () => Int = () => 42

    // CAREFUL!!!!!!
    println(justDoSomething)    // <- function itself <- instance
    println(justDoSomething())  // <- call <- value

    // curly braces with lamdas

    val stringToInt = {
        (str: String) =>
        str.toInt
    }

    // MOAR syntactic sugar
    val nicerIncrementer: Int => Int = _ + 1
    val niceAdder: (Int, Int) => Int = _ + _

    val superAdd = (x: Int) => (y: Int) => x +y
    println(superAdd(3)(2))
}
