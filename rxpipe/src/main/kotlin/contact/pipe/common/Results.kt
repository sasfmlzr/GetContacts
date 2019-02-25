package contact.pipe.common

import contact.api.model.contact.OwnerContacts
import contact.architecture.EventModel

data class ObserveContactsOwnerEventModel(
        val contacts: List<OwnerContacts>? = listOf()
) : EventModel()
