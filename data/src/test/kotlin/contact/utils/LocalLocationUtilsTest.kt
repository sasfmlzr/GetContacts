package contact.utils

import contact.api.location.GetLocation
import org.joda.time.LocalDateTime
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocalLocationUtilsTest {

    @InjectMocks
    lateinit var localLocationUtils: LocalLocationUtils

    private val dateCurrent = LocalDateTime()
    private val locationOne = GetLocation(1.0, 0.0, dateCurrent)
    private val dateMin = dateCurrent.minusMonths(1)
    private val locationTwo = GetLocation(3.0, 2.0, dateMin)
    private val dateMax = dateCurrent.plusMonths(1)
    private val locationThree = GetLocation(2.0, 4.0, dateMax)

    @Test
    fun `Getting min max dates from locations was success when many locations`() {
        val locations = listOf(locationOne, locationTwo, locationThree)
        localLocationUtils.getMinMaxDate(locations)
                .test()
                .assertValue(Pair(dateMin, dateMax))
                .assertComplete()
                .assertNoErrors()
    }

    @Test
    fun `Getting min max dates from locations was success when one locations`() {
        val locations = listOf(locationOne)
        localLocationUtils.getMinMaxDate(locations)
                .test()
                .assertValue(Pair(dateCurrent, dateCurrent))
                .assertComplete()
                .assertNoErrors()
    }

    @Test
    fun `Getting min max dates from locations emits error, when list without values`() {
        val locations = listOf<GetLocation>()
        localLocationUtils.getMinMaxDate(locations)
                .test()
                .assertNoValues()
                .assertErrorMessage("Location could not be empty")
    }
}
