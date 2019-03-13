package contact.pipe.location

import contact.api.location.GetLocation
import contact.architecture.ErrorEventModel
import contact.base.TestEvent
import contact.base.willReturn
import contact.base.willReturnError
import contact.usecase.feature.GetMinMaxDateUseCase
import io.reactivex.Observable
import org.joda.time.LocalDateTime
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class GetMinMaxDatePipeTest{

    @InjectMocks
    lateinit var pipe: GetMinMaxDatePipe

    @Mock
    lateinit var getMinMaxDate: GetMinMaxDateUseCase

    private val locations = listOf(GetLocation(1.0, 0.0, LocalDateTime()))

    @Test
    fun `Test event does not emit anything`() {
        Observable.just(TestEvent())
                .compose(pipe)
                .test()
                .assertNoValues()
                .assertNoErrors()
    }

    @Test
    fun `Getting dates are emitted on success`() {
        val result = Pair(LocalDateTime(), LocalDateTime())
        getMinMaxDate.willReturn(locations, result)

        Observable.just(RequestMinMaxDateEvent(locations))
                .compose(pipe)
                .test()
                .assertValues(
                        MinMaxDateEventModel(result.first, result.second)
                )
                .assertNoErrors()
    }

    @Test
    fun `Getting dates emits error`() {
        val error = Exception()
        getMinMaxDate.willReturnError(locations, error)

        Observable.just(RequestMinMaxDateEvent(locations))
                .compose(pipe)
                .test()
                .assertValues(ErrorEventModel(error))
                .assertNoErrors()
    }
}
