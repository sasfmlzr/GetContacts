package contact.fragment.contacts

import contact.architecture.Presenter
import contact.pipe.contacts.ContactsState
import javax.inject.Inject

class ContactsPresenter @Inject constructor() : Presenter<ContactsState, ContactsModel>() {

    override fun map(state: ContactsState): ContactsModel = ContactsModel(
            isError = state.isError,
            errorMessage = state.error?.message?: "something error",
            contactsResult = state.contactsResult
    )

}
