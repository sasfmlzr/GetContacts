package contact.pipe.location

import contact.architecture.EventModel
import contact.architecture.Funnel
import contact.pipe.contactsowner.ObserveContactsOwnerEventModel

class LocationFunnel(initState: LocationState) : Funnel<LocationState>(initState) {
    override fun reduce(state: LocationState, eventModel: EventModel): LocationState {
        return when (eventModel) {
            is LocationEventModel -> state.copy(eventModel = eventModel)
            else -> state
        }
    }
}
