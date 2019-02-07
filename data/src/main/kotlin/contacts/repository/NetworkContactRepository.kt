package contacts.repository

import contacts.api.ContactsApi
import contact.api.model.contact.OwnerContacts
import contact.repository.ContactRepository
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

internal class NetworkContactRepository @Inject constructor(
        private val contactsApi: ContactsApi
) : ContactRepository {

    override fun getAllOwnerContacts(): Single<List<OwnerContacts>> {
        return contactsApi.getAllOwnerContacts()
    }
}
