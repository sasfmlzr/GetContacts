package contact.pipe.contacts

import contact.architecture.ViewState

data class ContactsState (
        val isError: Boolean = false,
        val error: Throwable? = null,
        val contactsResult: String? = null
): ViewState {
    companion object {
        fun idle() = ContactsState()
    }
}
