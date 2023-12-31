package Section_4

import Section_4.TuplesAndMaps.{friend, network}

import scala.annotation.tailrec

object TuplesAndMaps extends App
{
    val aTuple = new Tuple2(2, "hello, Scala")  // is the same as Tuple2[Int, String](2, "hello, Scala)

    println(aTuple._2)
    println(aTuple.copy(_2 = "goodbye Java"))
    println(aTuple.swap)

    // Maps - keys -> values
    val aMap: Map[String, Int] = Map()

    val phoneBook = Map(("Jim", 555), "Daniel" -> 789, ("JIM", 900)).withDefaultValue(-1)
    // a -> b = syntactyc sugar

    println(phoneBook)

    // Map operations
    println(phoneBook.concat("Jim"))
    println(phoneBook("Jim"))
    println(phoneBook("Mary"))

    val newPairing = "Mary" -> 678
    val newPhoneBook = phoneBook + newPairing
    println(newPhoneBook)

    println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))

    // filterKeys
    println(phoneBook.filterKeys(x => x.startsWith("J")))

    // mapValues
    println(phoneBook.mapValues(number => "0245-" + number))

    // conversions to other collections
    println(phoneBook.toList)
    println(List(("Daniel", 555)).toMap)
    val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
    println(names.groupBy(name => name.charAt(0)))

    /**
     *  Exercise
     *  1. What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900?
     *  2. Overly simplified social network based on maps
     *      Person = string
     *      - add a person to the network
     *      - remove
     *      - friend ( mutal )
     *      - unfriend
     *
     *      - number of friends of a person
     *      - person with most friends
     *      - how many people have NO friends
     *      - if there is a social connection between two people ( direct or not )
     */

    // 1

    // wenn man 2 mal den gleichen key hat ( bin mir aber bei value nicht sicher ), dann kann es sein, dass wenn man z.B. .toLowerCase auf der Map aufruft,
    // dass dann der doppelte key ( und datensatz ) verschwindet

    // 2

    def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
        network + (person -> Set())

    def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
    {
        val friendsA = network(a)
        val friendsB = network(b)

        network + (a -> (friendsA + b)) + (b -> (friendsB + a))
    }

    def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
    {
        val friendsA = network(a)
        val friendsB = network(b)

        network + (a -> (friendsA - b)) + (b -> (friendsB - a))
    }

    def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    {
        def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
            if (friends.isEmpty) networkAcc
            else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

        val unfriended = removeAux(network(person), network)
        unfriended - person
    }

    val empty: Map[String, Set[String]] = Map()
    val network = add(add(empty, "Bob"), "Mary")
    println(network)
    println(friend(network, "Bob", "Mary"))
    println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
    println(remove(friend(network, "Bob", "Mary"), "Bob"))

    // Jim,Bob,Mary
    val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
    val jimBob = friend(people, "Bob", "Jim")
    val testNet = friend(jimBob, "Bob", "Mary")

    println(testNet)

    def nFriends(network: Map[String, Set[String]], person: String): Int =
        if (!network.contains(person)) 0
        else network(person).size

    println(nFriends(testNet, "Bob"))

    def mostFriends(network: Map[String, Set[String]]): String =
    {
        network.maxBy(pair => pair._2.size)._1
    }

    println(mostFriends(network))

    def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    {
        network.count(pair => pair._2.isEmpty)
    }

    println(nPeopleWithNoFriends(testNet))

    def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean =
    {
        @tailrec
        def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean =
        {
            if(discoveredPeople.isEmpty) false
            else
            {
                val person = discoveredPeople.head

                if(person == target) true
                else if(consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
                else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
            }
        }

        bfs(b, Set(), network(a) + a)
    }

    println(socialConnection(testNet, "Mary", "Jim"))
    println(socialConnection(network, "Mary", "Bob"))
}