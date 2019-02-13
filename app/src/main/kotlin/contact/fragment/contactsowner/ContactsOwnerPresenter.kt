package contact.fragment.contactsowner

import contact.architecture.Presenter
import contact.pipe.contactsowner.ContactsOwnerState
import javax.inject.Inject

class ContactsOwnerPresenter @Inject constructor() : Presenter<ContactsOwnerState, ContactsOwnerModel>() {

    override fun map(state: ContactsOwnerState): ContactsOwnerModel = ContactsOwnerModel(
            eventModel = state.eventModel
    )

}
