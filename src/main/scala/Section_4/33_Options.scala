package Section_4

import scala.util.Random

object Options extends App
{
    val myFirstOption: Option[Int] = Some(5)
    val noOption: Option[Int] = None

    println(myFirstOption)
    println(noOption)

    // unsafe APIS
    def unsafeMethod: String = null

    // val result = Some(null)  // This is wrong
    val result = Option(unsafeMethod)     // SOME OR NONE
    println(result)

    /**
     *  Exercise
     */

    val config: Map[String, String] = Map(
        // fetched from elsewhere
        "host" -> "176.45.36.1",
        "port" -> "80"
    )

    class Connection
    {
        def connect: String = "Connected"   // connect to some server
    }

    object Connection
    {
        val random: Random = new Random(System.nanoTime())

        def apply(host: String, port: String): Option[Connection] =
        {
            if(random.nextBoolean()) Some(new Connection)
            else None
        }
    }

    // my try:
    /*def mayBeConnect(config: Map[String, String], connection: Connection) =
    {
        if(Connection(config.get("host"), config.get("port")) == Some) connection.connect
        else
    }*/

    // solution:
    // try to establish a connection, if so - print the connect method
    val host = config.get("host")
    val port = config.get("port")
    /*
      if (h != null)
        if (p != null)
          return Connection.apply(h, p)
      return null
     */
    val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
    /*
      if (c != null)
        return c.connect
      return null
     */
    val connectionStatus = connection.map(c => c.connect)
    // if (connectionStatus == null) println(None) else print (Some(connectionstatus.get))
    println(connectionStatus)
    /*
      if (status != null)
        println(status)
     */
    connectionStatus.foreach(println)

    // chained call
    config.get("host")
        .flatMap(host => config.get("port")
            .flatMap(port => Connection(host, port))
            .map(connection => connection.connect)
        ).foreach(println)

    // for-comprehensions
    val forConnectionStatus = for
    {
        host <- config.get("host")
        port <- config.get("port")
        connection <- Connection(host, port)
    } yield connection.connect

    forConnectionStatus.foreach(println)
}