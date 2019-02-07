package contact.pipe.contacts

import contact.api.model.contact.OwnerContacts
import contact.architecture.EventModel

data class ContactsInitEventModel(
        val isError: Boolean = false,
        val error: Throwable? = null,
        val contactsResult: String? = null
) : EventModel()

data class ContactsPushEventModel(
        val contactsPushResult: String? = null
) : EventModel()

data class ObserveContactsEventModel(
        val contacts: List<OwnerContacts>? = listOf()
) : EventModel()
