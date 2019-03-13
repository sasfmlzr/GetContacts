package contact.api

import contact.api.location.GetLocation
import contact.api.model.contact.OwnerContacts
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ContactsApi {
    @GET("contacts/get")
    fun getAllOwnerContacts(): Single<List<OwnerContacts>>

    @GET("contacts/get")
    fun getOwnerContacts(): Call<List<OwnerContacts>>

    @GET("contacts/getlocation/{id}")
    fun getOwnerById(@Path("id") nameOwner: String): Call<GetLocation>
}
