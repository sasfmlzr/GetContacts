package contact.usecase.feature

import contact.api.location.Location
import contact.storage.ContactStorage
import contact.usecase.base.SingleUseCaseTest
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock

internal class ObserveLocationsByIdUseCaseTest : SingleUseCaseTest<List<Location>>() {

    @InjectMocks
    lateinit var observeLocationsByIdUseCase: ObserveLocationsByIdUseCase

    @Mock
    lateinit var contactsStorage: ContactStorage

    private val params = ObserveLocationsByIdUseCase.Params("test", LocalDate(), LocalDate())

    @Test
    fun `Happy pass`() {
        val result = listOf(Location(1.0, 0.0, LocalDateTime()))
        given(contactsStorage
                .getLocationById(params.id, params.fromDate, params.toDate))
                .willReturnSingle(result)

        whenSubscribed(observeLocationsByIdUseCase, params, observer)

        verifySuccess(result)
    }

    @Test
    fun `Observe locations by id use case emits error`() {
        BDDMockito.given(contactsStorage
                .getLocationById(params.id, params.fromDate, params.toDate))
                .willReturnSingleError()

        whenSubscribed(observeLocationsByIdUseCase, params, observer)

        verifyError()
    }
}
