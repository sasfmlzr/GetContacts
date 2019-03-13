package contact.repository

import contact.api.model.contact.OwnerContacts
import contact.api.ContactsApi
import io.reactivex.Single
import javax.inject.Inject

internal class NetworkContactRepository @Inject constructor(
        private val contactsApi: ContactsApi
) : ContactRepository {

    override fun getAllOwnerContacts(): Single<List<OwnerContacts>> {
        return contactsApi.getAllOwnerContacts()
    }
}
