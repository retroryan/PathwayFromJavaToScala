package pathwayToScala

import java.util.Date
import java.util

//import java Integer as JInt to make it easier to distinguish from a Scala Int
import java.lang.{Integer => JInt}

/**
 * case classes in Scala are by default immutable.  It does this by making the constructor parameters private and
 * only providing public accessors to the fields.  This class defines two constructor personId, personId and name, used
 * to represent a Person in the system.
 *
 * @param personId
 * @param name
 */
case class Person(personId:Int, name:String) {

}

/**
 * This class represents an Entry in the Count History and defines two constructor parameters, personId and timeStamp.
 *
 * @param person  - makes this a Java Integer to make it interoperating with Java classes easier.
 * @param timeStamp - notice how this parameter is a java Date, even though we are creating it in Scala.
 *
 * */
case class CountEntry(person:Person, timeStamp:Date) {

}


/** Object's are know as companion classes in Scala and are similar to a static singleton in Java.
  *
  *
  */
object CountEntry {


  //import the Java util List with the name JList to distinguish from Scala List
  import java.util.{List => JList}
  import java.util.{Map => JMap}
  import scala.collection.JavaConverters._
  import scala.collection.JavaConversions

  //This creates a map of person id to a list of countEntries for that person
  def getMapCountEntries():JMap[JInt, JList[CountEntry]] = {
    //get the count history which is a Java List as a Scala List
    val sCountHistory = CountingService.getInstance.getCountHistory.asScala

    //Create a map of each persons count entry by grouping the count history list by the person id
    val tmpPersonEntryMap = sCountHistory.groupBy(countEntry => countEntry.person.personId)

    //The groupBy returns a map of personId's to a Scala Buffer of CountEntries
    //We want to convert the map key from Scala Int to Java Integer and
    // the values from Buffers to a Java List so this is easier to work with from Java
    //This loops through each entry in the Map and extracts the key and value
    val personEntryMap = for {
      (personId, countEntryBuffer) <- tmpPersonEntryMap
    }  yield {
      personId.asInstanceOf[JInt] -> JavaConversions.seqAsJavaList(countEntryBuffer)
    }

    //there is no return here.  Scala automatically returns the last statement executed, so the following line returns the
    //map of values and that is what is returned   from the method.
    JavaConversions.mapAsJavaMap(personEntryMap)
  }


  /**
   *  This updates every instance of a person's id in the current count history list.  To avoid problems converting the
   *  list to Scala and back this iterates over the list and builds a new Array List.
   *
   * @param oldPersonId
   * @param newPersonId
   * @return
   */
  def updatePersonId(oldPersonId: Int, newPersonId: Int) = {
    //get the count history which is a Java List as a Scala List
    val countHistory = CountingService.getInstance.getCountHistory.asScala

    val updatedCountHistory = new util.ArrayList[CountEntry]

    for (countEntry: CountEntry <- countHistory) {
      val newCountEntry = countEntry.person match {
        //This matches the person based on the person id.  The backticks ` ` mean match the value in the variable
        //oldPersonId and the _ is a wildcard meaning match everything.
        case Person(`oldPersonId`, _) => {
          val newPerson = countEntry.person.copy(personId = newPersonId)
          countEntry.copy(person = newPerson)
        }
        case _ => countEntry //just return the current countEntry
      }
      updatedCountHistory.add(newCountEntry)
    }

    updatedCountHistory
  }

  /**
   *   This updates every instance of a person's id in the current count history list
   *
   *   Originally I tried converting the list to scala, mapping the values and returning a new Scala list.
   *   The problem was JavaConversions.seqAsJavaList returns an Abstract List which can not be used as an ArrayList
   *   in Java.
   *
   * @param oldPersonId
   * @param newPersonId
   * @return
   */
  def failedUpdatePersonId(oldPersonId:Int, newPersonId:Int) = {
    //get the count history which is a Java List as a Scala List
    val sCountHistory = CountingService.getInstance.getCountHistory.asScala

    val updatedCountHistory = sCountHistory.map {
      countEntry => {
        countEntry.person match {
          //This matches the person based on the person id.  The backticks ` ` mean match the value in the variable
          //oldPersonId and the _ is a wildcard meaning match everything.
          case Person(`oldPersonId`,_) => {
            val newPerson = countEntry.person.copy(personId = newPersonId)
            countEntry.copy(person = newPerson)
          }
          case _ => countEntry  //just return the current countEntry
        }
      }
    }
    JavaConversions.mutableSeqAsJavaList(updatedCountHistory)
  }

}