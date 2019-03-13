package contact.storage

import com.jakewharton.rxrelay2.BehaviorRelay
import contact.api.location.GetLocation
import contact.api.model.contact.Contact
import contact.api.model.contact.OwnerContacts
import contact.repository.ContactRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.joda.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LocalContactStorage @Inject constructor(
        private val contactRepository: ContactRepository
) : ContactStorage {

    private val relay = BehaviorRelay.createDefault<List<OwnerContacts>>(listOf())

    override fun fetch(): Completable =
            contactRepository.getAllOwnerContacts()
                    .map {list ->
                        list.forEach {ownerContacts ->
                            ownerContacts.contacts.sortWith(compareBy(Contact::name))
                        }
                        val sortedList = list.sortedWith(compareBy(OwnerContacts::id))
                        relay.accept(sortedList)
                    }.ignoreElement()

    override fun getAll(): Observable<List<OwnerContacts>> {
        return relay
    }

    override fun getLocationById(id: String,
                                 fromDate: LocalDate,
                                 toDate: LocalDate): Single<List<GetLocation>> {
        return Single.fromCallable {
            relay.value!!.find { it.id == id }?.locations?.filter {
                it.date.toLocalDate() >= fromDate && it.date.toLocalDate() <= toDate
            }
        }
    }
}
