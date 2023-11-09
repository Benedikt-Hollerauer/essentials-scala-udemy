package Section_3

object OOP extends App
{
    // Novel and Writer

    class Novel(name: String, yearOfRelease: Int, author: Writer)
    {
        def authorAge: Int = author.yearOfBirth
        def isWrittenBy(author: Writer): Boolean = author == this.author
        def copy(newYearOfRelease: Int): Novel = new Novel(this.name, newYearOfRelease, author)
    }

    class Writer(firstName: String, surName: String, val yearOfBirth: Int)
    {
        def fullName: String = s"$firstName, $surName"
    }

    val author: Writer = new Writer("Charles", "Dickins", 1812)
    val novel: Novel = new Novel("Great Expectations", 1861, author)

    println(novel.authorAge)
    println(novel.isWrittenBy(author))

    // Counter

    class Counter(val count: Int = 0)
    {
        def inc =
        {
            println("incrementing")
            new Counter(count + 1) // immutability
        }

        def dec =
        {
            println("decrementing")
            new Counter(count - 1)
        }

        def inc(n: Int): Counter =
        {
            if (n <= 0) this
            else inc.inc(n - 1)
        }

        def dec(n: Int): Counter =
            if (n <= 0) this
            else dec.dec(n - 1)

        def print = println(count)
    }

    val counter = new Counter
    counter.inc.print
    counter.inc.inc.inc.print
    counter.inc(10).print
}