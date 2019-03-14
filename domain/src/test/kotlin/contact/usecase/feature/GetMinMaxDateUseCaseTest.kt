package contact.usecase.feature

import contact.api.location.GetLocation
import contact.usecase.base.SingleUseCaseTest
import contact.utils.LocationUtils
import org.joda.time.LocalDateTime
import org.junit.Assert
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock

internal class GetMinMaxDateUseCaseTest : SingleUseCaseTest<Pair<LocalDateTime, LocalDateTime>>() {

    @InjectMocks
    lateinit var getMinMaxDateUseCase: GetMinMaxDateUseCase

    @Mock
    lateinit var locationUtils: LocationUtils

    @Test
    fun `Get min max date emits min max date`() {
        val locations = listOf(GetLocation(0.0, 0.0, LocalDateTime()),
                GetLocation(1.0, 1.0, LocalDateTime()),
                GetLocation(3.0, 3.0, LocalDateTime()))
        val pairDate = Pair(locations[0].date, locations[2].date)
        given(locationUtils.getMinMaxDate(locations)).willReturnSingle(pairDate)

        whenSubscribed(getMinMaxDateUseCase, locations, observer)

        verifySuccess(pairDate)
    }

    @Test
    fun `Get min max date error emits error`() {
        val locations = listOf<GetLocation>()
        given(locationUtils.getMinMaxDate(locations)).willReturnSingleError()

        whenSubscribed(getMinMaxDateUseCase, locations, observer)

        verifyError()
    }
}
