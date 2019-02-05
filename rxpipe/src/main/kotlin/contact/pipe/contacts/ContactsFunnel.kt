package contact.pipe.contacts

import contact.architecture.ErrorResult
import contact.architecture.Funnel
import contact.architecture.HideErrorResult
import contact.architecture.Result

class ContactsFunnel(initState: ContactsState) : Funnel<ContactsState>(initState) {
    override fun reduce(state: ContactsState, result: Result): ContactsState = when (result) {
        is ErrorResult -> state.copy(isError = true, error = result.error)
        is HideErrorResult -> state.copy(error = null, isError = false)
        is ContactsResult -> state.copy(contactsResult = result.contactsResult)
        else -> state
    }
}
