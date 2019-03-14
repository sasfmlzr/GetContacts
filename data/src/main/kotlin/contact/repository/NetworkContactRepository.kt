package contact.repository

import contact.api.model.contact.OwnerContacts
import contact.api.ContactsApi
import io.reactivex.Single
import java.lang.RuntimeException
import javax.inject.Inject

internal class NetworkContactRepository @Inject constructor(
        private val contactsApi: ContactsApi
) : ContactRepository {

    override fun getAllOwnerContacts(): Single<List<OwnerContacts>> {
        return try {
            contactsApi.getAllOwnerContacts()
        } catch (e: RuntimeException) {
            Single.fromCallable { throw e }
        }
    }
}
