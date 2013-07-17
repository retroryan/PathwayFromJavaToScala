import javaToScala.CountingService
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
    countingService.increment()
    countingService.increment()

    assertSame("The count should be 2", countingService.getCount, 2L)

  }
}
