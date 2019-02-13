package contact.pipe.contactsowner

import contact.api.model.contact.OwnerContacts
import contact.architecture.EventModel

data class ContactsOwnerInitEventModel(
        val isError: Boolean = false,
        val error: Throwable? = null,
        val contactsResult: String? = null
) : EventModel()

data class ContactsOwnerPushEventModel(
        val contactsPushResult: String? = null
) : EventModel()

data class ObserveContactsOwnerEventModel(
        val contacts: List<OwnerContacts>? = listOf()
) : EventModel()
