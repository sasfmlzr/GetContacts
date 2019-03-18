package contact.api.location

import com.google.gson.annotations.SerializedName
import org.joda.time.LocalDateTime


data class Location(@SerializedName("latitude") val latitude: Double,
                    @SerializedName("longitude") val longitude: Double,
                    @SerializedName("date") val date: LocalDateTime)
