package contact.api.model.contact

import com.google.gson.annotations.SerializedName

data class OwnerContacts(@SerializedName("id")
                         val id: String = "",
                         @SerializedName("contacts")
                         val contacts: MutableList<Contact> = mutableListOf())
