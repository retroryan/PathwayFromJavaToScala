package pathwayToScala

import java.util.Date

//import java Integer as JInt to make it easier to distinguish from a Scala Int
import java.lang.{Integer => JInt}

/**
 * case classes in Scala are by default immutable.  This class defines two constructor parameters, personId and timeStamp.
 * The case class also makes these parameters private fields with public accessors.
 *
 * @param personId  - makes this a Java Integer to make it interoprating with Java classes easier.
 * @param timeStamp - notice how this parameter is a java Date, even though we are creating it in Scala.
 *
 * */
case class CountEntry(personId:Int, timeStamp:Date) {

}


/** object's are know as companion classes in Scala and are the equivalent of a static singleton in Java.
  *
  */
object CountEntry {


  //import the Java util List with the name JList to distinguish from Scala List
  import java.util.{List => JList}
  import java.util.{Map => JMap}
  import scala.collection.JavaConverters._
  import scala.collection.JavaConversions

  def getNumberOfIncrements():JMap[JInt, JList[CountEntry]] = {
    //get the count history which is a Java List as a Scala List
    val sCountHistory = CountingService.getInstance.getCountHistory.asScala

    //Create a map of each persons count entry by grouping the count history list by the person id
    val tmpPersonEntryMap = sCountHistory.groupBy(countEntry => countEntry.personId)

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

}