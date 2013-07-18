package pathwayToScala

import org.junit.Test

//import all of the asserts from the Assert class.
import org.junit.Assert._

class CountingServiceTest {

  /**
   * Define a basic Scala method to use for testing
   */
  @Test
  def testCountingService() = {
    //scala infers the type of this variable to be a CountingService, so we don't have to declare the type
    val countingService = CountingService.getInstance()
    countingService.resetForTestOnly
    countingService.increment(2)
    countingService.increment(2)

    assertSame("The count should be 2", countingService.getCount, 2L)

  }

  @Test
  def testCountHistory() = {
    //scala infers the type of this variable to be a CountingService, so we don't have to declare the type
    val countingService = CountingService.getInstance
    countingService.resetForTestOnly
    countingService.increment(2)
    countingService.increment(2)

    //2 from the previous test plus 2 more
    assertSame("The count history should be of length 2", countingService.getCountHistory.size, 2)

  }
}
