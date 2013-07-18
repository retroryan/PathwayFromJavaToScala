package pathwayToScala

import java.util.Date

/**
 * case classes in Scala are by default immutable.  This class defines two constructor parameters, personId and timeStamp.
 * The case class also makes these parameters private fields with public accessors.
 *
 * @param personId
 * @param timeStamp - notice how this parameter is a java Date, even though we are creating it in Scala.
 *
 * */
case class CountEntry(personId:Int, timeStamp:Date) {

}

