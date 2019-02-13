package contact.pipe.location

import contact.architecture.EventModel
import contact.architecture.Funnel
import contact.pipe.contacts.ContactsInitEventModel
import contact.pipe.contacts.ContactsPushEventModel
import contact.pipe.contacts.ObserveContactsEventModel

class LocationFunnel(initState: LocationState) : Funnel<LocationState>(initState) {
    override fun reduce(state: LocationState, eventModel: EventModel): LocationState {
        return when (eventModel) {
            is ContactsInitEventModel -> state.copy(eventModel = eventModel)
            is ContactsPushEventModel -> state.copy(eventModel = eventModel)
            is ObserveContactsEventModel -> state.copy(eventModel = eventModel)
            else -> state
        }
    }
}
