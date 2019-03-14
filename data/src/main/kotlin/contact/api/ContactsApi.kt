package contact.api

import contact.api.model.contact.OwnerContacts
import io.reactivex.Single
import retrofit2.http.GET

interface ContactsApi {
    @GET("contacts/get")
    fun getAllOwnerContacts(): Single<List<OwnerContacts>>
}
