package contacts.storage

import com.jakewharton.rxrelay2.BehaviorRelay
import contact.api.model.contact.OwnerContacts
import contact.repository.ContactRepository
import contact.storage.ContactStorage
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LocalContactStorage @Inject constructor(
        private val contactRepository: ContactRepository
) : ContactStorage {

    private val relay = BehaviorRelay.createDefault<List<OwnerContacts>>(listOf())

    override fun fetch(): Completable =
            contactRepository.getAllOwnerContacts()
                    .map {
                        relay.accept(it)
                    }.ignoreElement()

    override fun getAll(): Observable<List<OwnerContacts>> {
        return relay
    }

}
