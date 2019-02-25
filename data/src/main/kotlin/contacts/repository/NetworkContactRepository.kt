package contacts.repository

import contact.api.model.contact.OwnerContacts
import contact.repository.ContactRepository
import contacts.api.ContactsApi
import io.reactivex.Single
import javax.inject.Inject

internal class NetworkContactRepository @Inject constructor(
        private val contactsApi: ContactsApi
) : ContactRepository {

    override fun getAllOwnerContacts(): Single<List<OwnerContacts>> {
        return contactsApi.getAllOwnerContacts()
    }
}
