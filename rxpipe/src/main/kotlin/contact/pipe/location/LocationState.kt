package contact.pipe.location

import contact.architecture.EventModel
import contact.architecture.ViewState

data class LocationState(
        val eventModel: EventModel? = null
) : ViewState {
    companion object {
        fun idle() = LocationState()
    }
}
