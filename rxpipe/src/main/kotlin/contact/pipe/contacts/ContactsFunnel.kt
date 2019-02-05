package contact.pipe.contacts

import contact.architecture.Funnel
import contact.architecture.Result

class ContactsFunnel(initState: ContactsState) : Funnel<ContactsState>(initState) {
    override fun reduce(state: ContactsState, result: Result): ContactsState {
        return when (result) {
            is ContactsInitEventModel ->  state.copy(result = result)
            is ContactsPushEventModel ->  state.copy(result = result)
            else ->  state
        }
    }
}
