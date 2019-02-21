package contact.api.location

import com.google.gson.annotations.SerializedName


data class GetLocation(@SerializedName("latitude") val latitude: Double,
                       @SerializedName("longitude") val longitude: Double,
                       @SerializedName("date") val date: org.joda.time.LocalDateTime)
