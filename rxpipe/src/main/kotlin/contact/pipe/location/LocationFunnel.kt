package contact.pipe.location

import contact.architecture.ErrorEventModel
import contact.architecture.EventModel
import contact.architecture.Funnel

class LocationFunnel(initState: LocationState) : Funnel<LocationState>(initState) {
    override fun reduce(state: LocationState, eventModel: EventModel): LocationState {
        return when (eventModel) {
            is LocationEventModel -> state.copy(eventModel = eventModel)
            is MinMaxDateEventModel -> state.copy(eventModel = eventModel)
            is ToolbarEventModel -> state.copy(eventModel = eventModel)
            is ErrorEventModel -> state.copy(eventModel = eventModel)
            else -> state.copy(eventModel = null)
        }
    }
}
