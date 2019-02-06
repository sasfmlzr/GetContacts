package contact.pipe.contacts

import contact.architecture.Funnel
import contact.architecture.EventModel

class ContactsFunnel(initState: ContactsState) : Funnel<ContactsState>(initState) {
    override fun reduce(state: ContactsState, eventModel: EventModel): ContactsState {
        return when (eventModel) {
            is ContactsInitEventModel -> state.copy(eventModel = eventModel)
            is ContactsPushEventModel -> state.copy(eventModel = eventModel)
            is ObserveContactsEventModel -> state.copy(eventModel = eventModel)
            else -> state
        }
    }
}
