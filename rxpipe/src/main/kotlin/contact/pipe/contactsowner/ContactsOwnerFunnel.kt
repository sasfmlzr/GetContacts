package contact.pipe.contactsowner

import contact.architecture.EventModel
import contact.architecture.Funnel
import contact.pipe.common.ObserveContactsOwnerEventModel

class ContactsOwnerFunnel(initState: ContactsOwnerState) : Funnel<ContactsOwnerState>(initState) {
    override fun reduce(state: ContactsOwnerState, eventModel: EventModel): ContactsOwnerState {
        return when (eventModel) {
            is ObserveContactsOwnerEventModel -> state.copy(eventModel = eventModel)
            else -> state
        }
    }
}
