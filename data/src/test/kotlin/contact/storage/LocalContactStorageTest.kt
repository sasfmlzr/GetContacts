package contact.storage

import contact.api.location.GetLocation
import contact.api.model.contact.Contact
import contact.api.model.contact.OwnerContacts
import contact.repository.ContactRepository
import io.reactivex.Single
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocalContactStorageTest {

    @InjectMocks
    internal lateinit var localContactStorage: LocalContactStorage

    @Mock
    lateinit var contactRepository: ContactRepository

    private val testContact = Contact("aaa", "0000")
    private val list: List<OwnerContacts> = listOf(OwnerContacts("aaa",
            mutableListOf(testContact, Contact("sss")),
            mutableListOf(GetLocation(1.0, 2.0, LocalDateTime()),
                    GetLocation(1.0, 2.0, LocalDateTime().minusMonths(1)))),
            OwnerContacts("bbb",
                    mutableListOf(Contact("asd", "2333"), Contact("sss")),
                    mutableListOf(GetLocation(1.0, 2.0, LocalDateTime()))),
            OwnerContacts("aba",
                    mutableListOf(Contact("asd"), Contact("sss")),
                    mutableListOf(GetLocation(1.0, 2.0, LocalDateTime()))))
    private val result: Single<List<OwnerContacts>> = Single.just(list)

    @Test
    fun `Get all contacts was success`() {
        list.forEach { ownerContacts ->
            ownerContacts.contacts.sortWith(compareBy(Contact::name))
        }
        val sortedList = list.sortedWith(compareBy(OwnerContacts::id))

        Assert.assertEquals(testContact.name, result.blockingGet().first().contacts.first().name)
        Assert.assertEquals(testContact.phone, result.blockingGet().first().contacts.first().phone)
        given(contactRepository.getAllOwnerContacts()).willReturn(result)
        localContactStorage.fetch().test()
                .assertComplete()
                .assertNoErrors()

        localContactStorage.getAll().test()
                .assertNotComplete()
                .assertNoErrors()
                .assertValue(sortedList)
    }

    @Test
    fun `Get locations by id contacts was success`() {
        val currentOwnerContact = list.first()
        val date = currentOwnerContact.locations.first().date.toLocalDate()

        given(contactRepository.getAllOwnerContacts()).willReturn(result)
        localContactStorage.fetch().test()
                .assertComplete()
                .assertNoErrors()

        localContactStorage.getLocationById(currentOwnerContact.id,
                date.minusYears(10), date)
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue(currentOwnerContact.locations)
    }

    @Test
    fun `Contacts repository emitted error`() {
        val error = RuntimeException("Test")
        given(contactRepository.getAllOwnerContacts()).willThrow(error)

        try {
            localContactStorage.fetch().test()
        } catch (e: RuntimeException) {
            Assert.assertEquals(e, error)
        }
    }

    @Test
    fun `Get locations by id emitted error`() {
        localContactStorage.getLocationById("test", LocalDate(), LocalDate())
                .test()
                .assertNotComplete()
                .assertErrorMessage("Location not find")
    }
}
