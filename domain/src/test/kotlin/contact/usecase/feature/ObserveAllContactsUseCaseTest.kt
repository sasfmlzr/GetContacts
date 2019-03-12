package contact.usecase.feature

import contact.api.model.contact.OwnerContacts
import contact.storage.ContactStorage
import contact.usecase.base.ObservableUseCaseTest
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock

internal class ObserveAllContactsUseCaseTest : ObservableUseCaseTest<List<OwnerContacts>>() {

    @InjectMocks
    lateinit var observeAllContactsUseCase: ObserveAllContactsUseCase

    @Mock
    lateinit var contactsStorage: ContactStorage

    @Test
    fun `Happy pass`() {
        val result = listOf(OwnerContacts())
        given(contactsStorage.getAll()).willReturn(result)

        whenSubscribed(observeAllContactsUseCase, observer)

        verifySuccessfulEmission(result)
    }

    @Test
    fun `Observe all contacts use case emits error`() {
        given(contactsStorage.getAll()).willReturnObservableError()

        whenSubscribed(observeAllContactsUseCase, observer)

        verifyError()
    }
}
