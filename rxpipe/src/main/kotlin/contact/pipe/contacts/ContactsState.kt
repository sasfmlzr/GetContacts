package contact.pipe.contacts

import contact.architecture.Result
import contact.architecture.ViewState

data class ContactsState (
        val result: Result? = null
): ViewState {
    companion object {
        fun idle() = ContactsState()
    }
}
