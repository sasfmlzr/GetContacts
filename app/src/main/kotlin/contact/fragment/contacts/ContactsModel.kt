package contact.fragment.contacts

import contact.architecture.base.ui.UiModel

data class ContactsModel(
        val isError: Boolean,
        val errorMessage: String,
        val contactsResult: String?
) : UiModel