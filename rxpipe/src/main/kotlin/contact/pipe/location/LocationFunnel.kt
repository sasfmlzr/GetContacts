package contact.pipe.location

import contact.architecture.EventModel
import contact.architecture.Funnel
import contact.pipe.common.ObserveContactsOwnerEventModel

class LocationFunnel(initState: LocationState) : Funnel<LocationState>(initState) {
    override fun reduce(state: LocationState, eventModel: EventModel): LocationState {
        return when (eventModel) {
            is ObserveContactsOwnerEventModel -> state.copy(eventModel = eventModel)
            else -> state
        }
    }
}
