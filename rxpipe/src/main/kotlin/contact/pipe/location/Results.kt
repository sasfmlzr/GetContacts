package contact.pipe.location

import contact.api.location.GetLocation
import contact.architecture.EventModel

data class LocationEventModel(
        val locations: List<GetLocation>
) : EventModel()
