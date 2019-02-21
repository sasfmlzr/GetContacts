package contacts.di

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import contact.api.location.GetLocation
import contact.api.model.contact.OwnerContacts
import contacts.api.ContactsApi
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton


@Module
internal class ApiModule {
    companion object {
        private const val CONTACT_URL = "192.168.1.112"
        private const val port = 8000
    }

    @Provides
    @Singleton
    fun getContactsRetrofitService(): ContactsApi {
        val httpUrl = HttpUrl.Builder()
                .host(CONTACT_URL)
                .port(port)
                .scheme("http")
                .build()

        val gson = GsonBuilder()
                .registerTypeAdapter(OwnerContacts::class.java,
                        JsonDeserializer<OwnerContacts> { json, _, _ ->
                            val jComment = json.asJsonObject
                            val gsonParser = GsonBuilder()
                                    .registerTypeAdapter(
                                            GetLocation::class.java,
                                            JsonDeserializer<GetLocation> { locationJson, _, _ ->
                                                val loc = locationJson as JsonObject
                                                val date = loc.get("date").asString
                                                val pattern = DateTimeFormat
                                                        .forPattern("YYYY-MM-dd HH:mm")
                                                GetLocation(loc.get("latitude")
                                                        .asDouble,
                                                        loc.get("longitude").asDouble,
                                                        org.joda.time.LocalDateTime
                                                                .parse(date, pattern))
                                            })
                                    .create()
                            val owner = gsonParser.fromJson(jComment, OwnerContacts::class.java)
                            owner
                        })
                .create()

        val gsonConverterFactory = GsonConverterFactory.create(gson)

        val retrofit = Retrofit.Builder()
                .baseUrl(httpUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(ContactsApi::class.java)
    }


}
