package contact.api.model.contact

import com.google.gson.annotations.SerializedName
import contact.api.location.Location

data class OwnerContacts(@SerializedName("id")
                         val id: String = "",
                         @SerializedName("contacts")
                         val contacts: MutableList<Contact> = mutableListOf(),
                         @SerializedName("locations")
                         val locations: MutableList<Location> = mutableListOf())
