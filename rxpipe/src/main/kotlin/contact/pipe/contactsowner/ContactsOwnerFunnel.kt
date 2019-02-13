package contact.pipe.contactsowner

import contact.architecture.EventModel
import contact.architecture.Funnel

class ContactsOwnerFunnel(initState: ContactsOwnerState) : Funnel<ContactsOwnerState>(initState) {
    override fun reduce(state: ContactsOwnerState, eventModel: EventModel): ContactsOwnerState {
        return when (eventModel) {
            is ContactsOwnerInitEventModel -> state.copy(eventModel = eventModel)
            is ContactsOwnerPushEventModel -> state.copy(eventModel = eventModel)
            is ObserveContactsOwnerEventModel -> state.copy(eventModel = eventModel)
            else -> state
        }
    }
}
