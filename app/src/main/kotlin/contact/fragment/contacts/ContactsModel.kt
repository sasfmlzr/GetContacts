package contact.fragment.contacts

import contact.architecture.base.ui.UiModel
import contact.architecture.Result

data class ContactsModel(
        val result: Result? = null
) : UiModel
