package contact.repository

import contact.api.ContactsApi
import contact.api.model.contact.OwnerContacts
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NetworkContactRepositoryTest {

    @InjectMocks
    internal lateinit var networkContactRepository: NetworkContactRepository

    @Mock
    lateinit var contactsApi: ContactsApi

    @Test
    fun `Getting contact owners was successful`() {
        val result = listOf(OwnerContacts("test"))

        given(contactsApi.getAllOwnerContacts()).willReturn(Single.just(result))

        networkContactRepository.getAllOwnerContacts().test()
                .assertNoErrors()
                .assertComplete()
                .assertValue(result)
    }

    @Test
    fun `Getting contact owners emitted error`() {
        val error = RuntimeException("test error")
        given(contactsApi.getAllOwnerContacts()).willThrow(error)

        networkContactRepository.getAllOwnerContacts().test()
                .assertNotComplete()
                .assertError(error)
                .assertNoValues()
    }
}
