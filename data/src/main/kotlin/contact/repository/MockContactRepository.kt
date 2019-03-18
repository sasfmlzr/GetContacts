package contact.repository

import contact.api.location.GetLocation
import contact.api.model.contact.Contact
import contact.api.model.contact.OwnerContacts
import io.reactivex.Single
import org.joda.time.LocalDateTime
import javax.inject.Inject

internal class MockContactRepository @Inject constructor() : ContactRepository {
    override fun getAllOwnerContacts(): Single<List<OwnerContacts>> {
        val contacts = mutableListOf(
                Contact("aaa", "9999"),
                Contact("bbb", "8888"))
        val locations = mutableListOf(
                GetLocation(47.2368525, 38.9243765,
                        LocalDateTime()),
                GetLocation(47.1972209, 38.9280983,
                        LocalDateTime().minusDays(1)),
                GetLocation(47.2248412, 38.8958571,
                        LocalDateTime().minusDays(3)),
                GetLocation(47.2332899, 38.8926696,
                        LocalDateTime().minusDays(7)))
        val ownerList = OwnerContacts("test", contacts, locations)

        return Single.just(listOf(ownerList))
    }
}
