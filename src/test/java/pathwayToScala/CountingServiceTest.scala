package pathwayToScala

import org.junit.Test

//import all of the asserts from the Assert class.
import org.junit.Assert._

import scala.collection.JavaConverters._

class CountingServiceTest {

  val testPersonId = 2
  val testPerson: Person = Person(testPersonId, "Elli")

  /**
   * Define a basic Scala method to use for testing
   */
  @Test
  def testCountingService() = {
    //scala infers the type of this variable to be a CountingService, so we don't have to declare the type
    val countingService = CountingService.getInstance()
    countingService.resetForTestOnly

    countingService.increment(testPerson)
    countingService.increment(testPerson)

    assertSame("The count should be 2", countingService.getCount, 2L)

  }

  @Test
  def testCountHistory() = {
    //scala infers the type of this variable to be a CountingService, so we don't have to declare the type
    val countingService = CountingService.getInstance
    countingService.resetForTestOnly
    countingService.increment(testPerson)
    countingService.increment(testPerson)

    //2 from the previous test plus 2 more
    assertSame("The count history should be of length 2", countingService.getCountHistory.size, 2)

  }

  @Test
  def testUpdateCountHistory() = {
    //scala infers the type of this variable to be a CountingService, so we don't have to declare the type
    val countingService = CountingService.getInstance
    countingService.resetForTestOnly
    countingService.increment(testPerson)
    countingService.increment(testPerson)
    countingService.updatePersonId(testPersonId, 22)

    //take the original count history list, convert it to scala and filter it so only entries that have a person id of 22 are left
    //if the update worked this new list should have 2 entries.
    val filteredList = countingService.getCountHistory.asScala.filter(countEntry => (countEntry.person.personId == 22))

    //2 from the previous test plus 2 more
    assertSame("The filtered count history should be of length 2", filteredList.size, 2)

  }
}
