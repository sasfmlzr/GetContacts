package contact.pipe.location

import contact.api.location.GetLocation
import contact.architecture.ErrorEventModel
import contact.base.willReturn
import contact.base.willReturnError
import contact.usecase.feature.ObserveLocationsByIdUseCase
import io.reactivex.Observable
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class ObserveLocationsByIdPipeTest {

    @InjectMocks
    lateinit var pipe: ObserveLocationsByIdPipe
    @Mock
    lateinit var observeLocationsById: ObserveLocationsByIdUseCase

    private val params = ObserveLocationsByIdUseCase.Params("test", LocalDate(), LocalDate())

    @Test
    fun `Loading locations are emitted on success`() {
        val result = listOf(GetLocation(1.0, 0.0, LocalDateTime()))

        observeLocationsById.willReturn(params, result)

        val locationEventModel = LocationEventModel(result)
        val toolbarString = "From ${params
                .fromDate.toString("yyyy.MM.dd")} to ${params
                .toDate.toString("yyyy.MM.dd")}"
        val toolbarEventModel = ToolbarEventModel(toolbarString)

        Observable.just(RequestLocationsByIdAndDateEvent(params.id, params.fromDate, params.toDate))
                .compose(pipe)
                .test()
                .assertValues(
                        locationEventModel,
                        toolbarEventModel
                )
                .assertNoErrors()

        Assert.assertEquals(result, locationEventModel.locations)
        Assert.assertEquals(toolbarString, toolbarEventModel.title)
    }

    @Test
    fun `Loading locations emits error`() {
        val error = Exception()
        observeLocationsById.willReturnError(params, error)

        Observable.just(RequestLocationsByIdAndDateEvent(params.id, params.fromDate, params.toDate))
                .compose(pipe)
                .test()
                .assertValues(ErrorEventModel(error))
                .assertNoErrors()
    }
}
