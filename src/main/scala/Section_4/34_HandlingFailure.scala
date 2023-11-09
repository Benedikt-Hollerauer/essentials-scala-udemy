package Section_4

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App
{
    val aSuccess = Success(3)
    val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

    println(aSuccess)
    println(aFailure)

    def unsafeMethod: String = throw new RuntimeException("NO STRING FOR YOU BUSTER")

    val potentialFailure = Try(unsafeMethod)
    println(potentialFailure)

    /**
     *  Exercise
     */

    // my take
    /*val hostname = "localhost"
    val port = "8080"

    def renderHtml(page: String): Unit = println(page)

    class Connection
    {
        def get(url: String): String =
        {
            val random = new Random(System.nanoTime())
            if(random.nextBoolean()) "<html>...</html>"
            else throw new RuntimeException("Connection interrupted")
        }
    }

    object HttpService
    {
        val random = new Random(System.nanoTime())

        def getConnection(host: String, port: String): Connection =
        {
            if(random.nextBoolean()) new Connection
            else throw new RuntimeException("Someone else took the port")
        }
    }

    // if you get the html page from  the connection, print it to the console i.e. call renderHTML

    // das ist imperativ
    val mayBeSuccess = Try{
        val mayBeConnection = HttpService.getConnection(hostname, port)
        println(mayBeConnection.get("website"))
    }

    println(mayBeSuccess)*/

    // better solution
    val host = "localhost"
    val port = "8080"

    def renderHTML(page: String) = println(page)

    class Connection
    {
        def get(url: String): String =
        {
            val random = new Random(System.nanoTime())
            if (random.nextBoolean()) "<html>...</html>"
            else throw new RuntimeException("Connection interrupted")
        }

        def getSafe(url: String): Try[String] = Try(get(url))
    }

    object HttpService
    {
        val random = new Random(System.nanoTime())

        def getConnection(host: String, port: String): Connection =
            if (random.nextBoolean()) new Connection
            else throw new RuntimeException("Someone else took the port")

        def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
    }

    // if you get the html page from the connection, print it to the console i.e. call renderHTML
    val possibleConnection = HttpService.getSafeConnection(host, port)
    val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/home"))
    possibleHTML.foreach(renderHTML)

    // shorthand version
    HttpService.getSafeConnection(host, port)
        .flatMap(connection => connection.getSafe("/home"))
        .foreach(renderHTML)

    // for-comprehension version
    for
    {
        connection <- HttpService.getSafeConnection(host, port)
        html <- connection.getSafe("/home")
    } renderHTML(html)
}