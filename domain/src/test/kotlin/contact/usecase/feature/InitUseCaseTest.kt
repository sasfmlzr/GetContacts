package contact.usecase.feature

import contact.storage.ContactStorage
import contact.usecase.base.CompletableUseCaseTest
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock

internal class InitUseCaseTest : CompletableUseCaseTest() {

    @InjectMocks
    lateinit var initUseCase: InitUseCase
    @Mock
    lateinit var contactsStorage: ContactStorage

    @Test
    fun `Happy pass`() {
        given(contactsStorage.fetch()).willComplete()

        whenSubscribed(initUseCase, observer)

        verifySuccess()
    }

    @Test
    fun `Init use case emits error`() {
        given(contactsStorage.fetch()).willReturnCompletableError()

        whenSubscribed(initUseCase, observer)

        verifyError()
    }
}
