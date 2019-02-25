package contact.pipe.location

import contact.architecture.EventModel
import contact.architecture.Funnel

class LocationFunnel(initState: LocationState) : Funnel<LocationState>(initState) {
    override fun reduce(state: LocationState, eventModel: EventModel): LocationState {
        return when (eventModel) {
            else -> state
        }
    }
}
