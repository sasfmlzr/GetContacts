package contact.fragment.contacts

import contact.architecture.base.ui.UiModel
import contact.architecture.EventModel

data class ContactsModel(
        val eventModel: EventModel? = null
) : UiModel
