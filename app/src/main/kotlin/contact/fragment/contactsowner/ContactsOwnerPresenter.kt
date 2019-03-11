package contact.fragment.contactsowner

import contact.architecture.Presenter
import contact.architecture.base.ui.BaseModel
import contact.pipe.contactsowner.ContactsOwnerState
import javax.inject.Inject

class ContactsOwnerPresenter @Inject constructor() : Presenter<ContactsOwnerState>() {

    override fun map(state: ContactsOwnerState): BaseModel = BaseModel (
            eventModel = state.eventModel
    )

}
