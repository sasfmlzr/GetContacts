package contact.api

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import contact.api.location.Location
import contact.api.model.contact.OwnerContacts
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import retrofit2.converter.gson.GsonConverterFactory

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
                Location::class.java,
                JsonDeserializer<Location> { locationJson, _, _ ->
                    val loc = locationJson as JsonObject
                    val date = loc.get("date").asString
                    val pattern = DateTimeFormat
                            .forPattern("YYYY-MM-dd HH:mm")
                    Location(loc.get("latitude")
                            .asDouble,
                            loc.get("longitude").asDouble,
                            LocalDateTime
                                    .parse(date, pattern))
                })
        .create()
