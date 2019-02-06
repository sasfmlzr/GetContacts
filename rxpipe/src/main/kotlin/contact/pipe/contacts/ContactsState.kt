package contact.pipe.contacts

import contact.architecture.EventModel
import contact.architecture.ViewState

data class ContactsState (
        val eventModel: EventModel? = null
): ViewState {
    companion object {
        fun idle() = ContactsState()
    }
}
