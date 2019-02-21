package contact.api.location

import com.google.gson.annotations.SerializedName
import java.util.*

data class GetLocation(@SerializedName("latitude") val name: Double,
                       @SerializedName("longitude") val phone: Double,
                       @SerializedName("date") val date: Date)
