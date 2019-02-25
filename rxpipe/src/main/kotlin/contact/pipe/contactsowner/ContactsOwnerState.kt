package contact.pipe.contactsowner

import contact.architecture.EventModel
import contact.architecture.ViewState

data class ContactsOwnerState(
        val eventModel: EventModel? = null
) : ViewState {
    companion object {
        fun idle() = ContactsOwnerState()
    }
}
