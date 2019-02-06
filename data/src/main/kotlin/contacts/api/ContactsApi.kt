package contacts.api

import contact.api.model.contact.OwnerContacts
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface ContactsApi {
    @GET("contacts/get")
    fun getAllOwnerContacts(): Single<List<OwnerContacts>>

    @GET("contacts/get")
    fun getOwnerContacts(): Call<List<OwnerContacts>>
}
