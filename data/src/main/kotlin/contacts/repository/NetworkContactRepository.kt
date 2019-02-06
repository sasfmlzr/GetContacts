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
        contactsApi.getOwnerContacts().enqueue(callback())
        return contactsApi.getAllOwnerContacts()
    }

    private fun callback(): Callback<List<OwnerContacts>> {
        return object : Callback<List<OwnerContacts>> {
            override fun onResponse(call: Call<List<OwnerContacts>>,
                                    response: Response<List<OwnerContacts>>) {
                print("sada")
            }

            override fun onFailure(call: Call<List<OwnerContacts>>, t: Throwable) {
               print("sada")
            }
        }
    }
}
