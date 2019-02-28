package contacts.api

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import contact.api.location.GetLocation
import contact.api.model.contact.OwnerContacts
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import retrofit2.converter.gson.GsonConverterFactory

class ContactsConverterFactory {

    companion object {
        fun getOwnerContactConverterFactory(): GsonConverterFactory {
            val ownerContactsParser = GsonBuilder()
                    .registerTypeAdapter(OwnerContacts::class.java,
                            JsonDeserializer<OwnerContacts> { json, _, _ ->
                                val jComment = json.asJsonObject
                                val owner = getLocationParser().fromJson(jComment,
                                        OwnerContacts::class.java)
                                owner
                            })
                    .create()
            return GsonConverterFactory.create(ownerContactsParser)
        }

        private fun getLocationParser() = GsonBuilder()
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
                                    LocalDateTime
                                            .parse(date, pattern))
                        })
                .create()
    }
}
